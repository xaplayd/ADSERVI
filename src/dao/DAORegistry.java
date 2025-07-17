package dao;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DAORegistry {

    private static final Map<String, DAO<?>> daoMap = new HashMap<>();

    static {
        try {
            // Supondo que seus DAOs estejam no pacote "dao"
            String daoPackage = "dao";
            String path = "src/" + daoPackage.replace('.', '/'); // ou "bin/" se já compilado

            File[] files = new File(path).listFiles((dir, name) -> name.endsWith("DAOImpl.class"));

            if (files != null) {
                for (File file : files) {
                    String className = file.getName().replace(".class", "");
                    String fullClassName = daoPackage + "." + className;

                    Class<?> clazz = Class.forName(fullClassName);

                    // Verifica se é um DAO, não é abstrato e implementa DAO
                    if (DAO.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                        DAO<?> dao = (DAO<?>) clazz.getDeclaredConstructor().newInstance();

                        // Usa a chave primária como chave do mapa
                        daoMap.put(dao.getChavePrimaria(), dao);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar DAOs automaticamente.");
        }
    }

    public static DAO<?> getDAOByPrimaryKey(String chavePrimaria) {
        return daoMap.get(chavePrimaria);
    }

    public static Set<String> getChavesRegistradas() {
        return daoMap.keySet();
    }
}