package by.chmut.catalog.dao;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final CatalogDAO catalogDAO = new CatalogDAOImpl();

    private final JAXBparser parser = new JAXBparserImpl();


    public CatalogDAO getCatalogDAO() {
        return catalogDAO;
    }

    public JAXBparser getParser() {
        return parser;
    }

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

}
