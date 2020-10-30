//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.10.29 à 11:29:20 AM CET 
//


package soapserver.api.book_ws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soapserver.api.book_ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soapserver.api.book_ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookByIdResponse }
     * 
     */
    public GetBookByIdResponse createGetBookByIdResponse() {
        return new GetBookByIdResponse();
    }

    /**
     * Create an instance of {@link BookType }
     * 
     */
    public BookType createBookType() {
        return new BookType();
    }

    /**
     * Create an instance of {@link GetAllBookRequest }
     * 
     */
    public GetAllBookRequest createGetAllBookRequest() {
        return new GetAllBookRequest();
    }

    /**
     * Create an instance of {@link GetAllBookResponse }
     * 
     */
    public GetAllBookResponse createGetAllBookResponse() {
        return new GetAllBookResponse();
    }

    /**
     * Create an instance of {@link DeleteBookResponse }
     * 
     */
    public DeleteBookResponse createDeleteBookResponse() {
        return new DeleteBookResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link UpdateBookResponse }
     * 
     */
    public UpdateBookResponse createUpdateBookResponse() {
        return new UpdateBookResponse();
    }

    /**
     * Create an instance of {@link GetBookByIdRequest }
     * 
     */
    public GetBookByIdRequest createGetBookByIdRequest() {
        return new GetBookByIdRequest();
    }

    /**
     * Create an instance of {@link GetAllBookByAuthorIdResponse }
     * 
     */
    public GetAllBookByAuthorIdResponse createGetAllBookByAuthorIdResponse() {
        return new GetAllBookByAuthorIdResponse();
    }

    /**
     * Create an instance of {@link DeleteBookRequest }
     * 
     */
    public DeleteBookRequest createDeleteBookRequest() {
        return new DeleteBookRequest();
    }

    /**
     * Create an instance of {@link GetAllBookByAuthorIdRequest }
     * 
     */
    public GetAllBookByAuthorIdRequest createGetAllBookByAuthorIdRequest() {
        return new GetAllBookByAuthorIdRequest();
    }

    /**
     * Create an instance of {@link AddBookRequest }
     * 
     */
    public AddBookRequest createAddBookRequest() {
        return new AddBookRequest();
    }

    /**
     * Create an instance of {@link UpdateBookRequest }
     * 
     */
    public UpdateBookRequest createUpdateBookRequest() {
        return new UpdateBookRequest();
    }

}
