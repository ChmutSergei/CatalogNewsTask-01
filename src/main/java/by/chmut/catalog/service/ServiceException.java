package by.chmut.catalog.service;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 4636210583809387706L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
