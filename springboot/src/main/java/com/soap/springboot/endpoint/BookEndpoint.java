package com.soap.springboot.endpoint;

import com.soap.springboot.entity.BookEntity;
import soapserver.api.book_ws.*;
import com.soap.springboot.service.BookEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class BookEndpoint {

    public static final String NAMESPACE_URI = "http://soapserver/api/book-ws";

    private BookEntityService service;

    public BookEndpoint() {

    }

    @Autowired
    public BookEndpoint(BookEntityService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse getBookById(@RequestPayload GetBookByIdRequest request) {
        GetBookByIdResponse response = new GetBookByIdResponse();
        BookEntity bookEntity = service.getBookById(request.getBookId());
        BookType bookType = new BookType();
        BeanUtils.copyProperties(bookEntity, bookType);
        response.setBookType(bookType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBookRequest")
    @ResponsePayload
    public GetAllBookResponse getAllBook(@RequestPayload GetAllBookRequest request) {
        GetAllBookResponse response = new GetAllBookResponse();
        List<BookType> bookTypeList = new ArrayList<BookType>();
        List<BookEntity> bookEntityList = service.getAllBook();
        for (BookEntity entity : bookEntityList) {
            BookType bookType = new BookType();
            BeanUtils.copyProperties(entity, bookType);
            bookTypeList.add(bookType);
        }
        response.getBookType().addAll(bookTypeList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBookByAuthorIdRequest")
    @ResponsePayload
    public GetAllBookByAuthorIdResponse getAllBookByAuthorIdRequest(@RequestPayload GetAllBookByAuthorIdRequest request) {
        GetAllBookByAuthorIdResponse response = new GetAllBookByAuthorIdResponse();
        List<BookType> bookTypeList = new ArrayList<BookType>();
        List<BookEntity> bookEntityList = service.getAllBookByAuthorId(request.getAuthorId());
        for (BookEntity entity : bookEntityList) {
            BookType bookType = new BookType();
            BeanUtils.copyProperties(entity, bookType);
            bookTypeList.add(bookType);
        }
        response.getBookType().addAll(bookTypeList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
    @ResponsePayload
    public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
        AddBookResponse response = new AddBookResponse();
        BookType newBookType = new BookType();
        ServiceStatus serviceStatus = new ServiceStatus();

        BookEntity newBookEntity = new BookEntity(request.getAuthorId(), request.getTitle(), request.getIsbn());
        BookEntity savedBookEntity = service.addBook(newBookEntity);

        if (savedBookEntity == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {
            BeanUtils.copyProperties(savedBookEntity, newBookType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }
        response.setBookType(newBookType);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBookRequest")
    @ResponsePayload
    public UpdateBookResponse updateBook(@RequestPayload UpdateBookRequest request) {
        UpdateBookResponse response = new UpdateBookResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        // 1. Find if book available
        BookEntity bookFromDB = service.getBookById(request.getBookId());
        if(bookFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Book = " + request.getTitle() + " not found");
        }else {
            // 2. Get updated book information from the request
            bookFromDB.setAuthorId(request.getAuthorId());
            bookFromDB.setTitle(request.getTitle());
            bookFromDB.setIsbn(request.getIsbn());
            // 3. update the book in database
            boolean flag = service.updateBook(bookFromDB);
            if(flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getTitle());;
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
    @ResponsePayload
    public DeleteBookResponse deleteBook(@RequestPayload DeleteBookRequest request) {
        DeleteBookResponse response = new DeleteBookResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        boolean flag = service.deleteBookById(request.getBookId());
        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deletint Entity id=" + request.getBookId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
