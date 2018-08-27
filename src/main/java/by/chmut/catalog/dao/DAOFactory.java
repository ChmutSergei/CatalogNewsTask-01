package by.chmut.catalog.dao;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final DAO DAO = new DAOImpl();

    private final JAXBparser parser = new JAXBparserImpl();


    public DAO getDAO() {
        return DAO;
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
