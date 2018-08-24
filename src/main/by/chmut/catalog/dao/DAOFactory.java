package by.chmut.catalog.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final CatalogDAO catalogDAO = new CatalogDAOImpl();


    public CatalogDAO getCatalogDAO() {
        return catalogDAO;
    }

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

}
