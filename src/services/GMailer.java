package services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

public class GMailer {

    private static final String TEST_EMAIL = "EMAIL HERE";
    private final Gmail service;

    public GMailer() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Mailer").build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws Exception {
        Path path = Path.of("C:/ws/data/secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
                new InputStreamReader(new FileInputStream(path.toFile()), "UTF-8"));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory,
                clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline").build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Envia email com HTML + inline + anexos reais
     */
    public void sendMail(String subject, String htmlContent, List<File> insertedImages) throws Exception {

        // Multipart raiz: mixed
        Multipart mixedMultipart = new MimeMultipart("mixed");

        // Parte HTML + inline
        MimeBodyPart relatedPart = new MimeBodyPart();
        Multipart relatedMultipart = new MimeMultipart("related");
        MimeBodyPart htmlPart = new MimeBodyPart();
        relatedMultipart.addBodyPart(htmlPart);

        // Processa todas as imagens inseridas
        int counter = 0;
        for (File file : insertedImages) {
            counter++;
            String cid = "img" + counter;
            String mimeType = java.nio.file.Files.probeContentType(file.toPath());
            if (mimeType == null) mimeType = "image/png";

            byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());

            // Cria parte inline
            MimeBodyPart inlinePart = new MimeBodyPart();
            DataSource dsInline = new ByteArrayDataSource(bytes, mimeType);
            inlinePart.setDataHandler(new DataHandler(dsInline));
            inlinePart.setHeader("Content-ID", "<" + cid + ">");
            inlinePart.setDisposition(MimeBodyPart.INLINE);
            relatedMultipart.addBodyPart(inlinePart);

            // Substitui a primeira tag <img> mantendo todos os atributos exceto src
            Pattern imgPattern = Pattern.compile("<img([^>]*)>");
            Matcher matcher = imgPattern.matcher(htmlContent);
            if (matcher.find()) {
                String originalAttrs = matcher.group(1);

                // Remove src existente, mas mantém width, height, style, class, etc.
                String newAttrs = originalAttrs.replaceAll("src\\s*=\\s*['\"][^'\"]*['\"]", "");
                String newImgTag = "<img src='cid:" + cid + "'" + newAttrs + ">";
                htmlContent = matcher.replaceFirst(Matcher.quoteReplacement(newImgTag));
            }
        }

        htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
        relatedPart.setContent(relatedMultipart);
        mixedMultipart.addBodyPart(relatedPart);

        // Adiciona os anexos reais
        for (File file : insertedImages) {
            MimeBodyPart attachment = new MimeBodyPart();
            byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());
            String mimeType = java.nio.file.Files.probeContentType(file.toPath());
            if (mimeType == null) mimeType = "application/octet-stream";

            DataSource dsAttach = new ByteArrayDataSource(bytes, mimeType);
            attachment.setDataHandler(new DataHandler(dsAttach));
            attachment.setFileName(file.getName());
            attachment.setDisposition(MimeBodyPart.ATTACHMENT);
            mixedMultipart.addBodyPart(attachment);
        }

        // Cria a mensagem
        Session session = Session.getDefaultInstance(System.getProperties(), null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL));
        email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(TEST_EMAIL));
        email.setSubject(subject, "UTF-8");
        email.setContent(mixedMultipart);

        // Codifica e envia via Gmail API
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        String encodedEmail = Base64.encodeBase64URLSafeString(buffer.toByteArray());

        Message msg = new Message();
        msg.setRaw(encodedEmail);

        service.users().messages().send("me", msg).execute();
    }
}