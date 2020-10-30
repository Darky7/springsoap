package com.soap.springboot.endpoint;

import com.soap.springboot.entity.AuthorEntity;
import soapserver.api.author_ws.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.soap.springboot.service.AuthorEntityService;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class AuthorEndpoint {

    public static final String NAMESPACE_URI = "http://soapserver/api/author-ws";

    private AuthorEntityService service;

    public AuthorEndpoint() {

    }

    @Autowired
    public AuthorEndpoint(AuthorEntityService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorByIdRequest")
    @ResponsePayload
    public GetAuthorByIdResponse getAuthorById(@RequestPayload GetAuthorByIdRequest request) {
        GetAuthorByIdResponse response = new GetAuthorByIdResponse();
        AuthorEntity authorEntity = service.getAuthorById(request.getAuthorId());
        AuthorType authorType = new AuthorType();
        BeanUtils.copyProperties(authorEntity, authorType);
        response.setAuthorType(authorType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAuthorRequest")
    @ResponsePayload
    public GetAllAuthorResponse getAllAuthor(@RequestPayload GetAllAuthorRequest request) {
        GetAllAuthorResponse response = new GetAllAuthorResponse();
        List<AuthorType> authorTypeList = new ArrayList<AuthorType>();
        List<AuthorEntity> authorEntityList = service.getAllAuthor();
        for (AuthorEntity entity : authorEntityList) {
            AuthorType authorType = new AuthorType();
            BeanUtils.copyProperties(entity, authorType);
            authorTypeList.add(authorType);
        }
        response.getAuthorType().addAll(authorTypeList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAuthorRequest")
    @ResponsePayload
    public AddAuthorResponse addAuthor(@RequestPayload AddAuthorRequest request) {
        AddAuthorResponse response = new AddAuthorResponse();
        AuthorType newAuthorType = new AuthorType();
        ServiceStatus serviceStatus = new ServiceStatus();

        AuthorEntity newAuthorEntity = new AuthorEntity(request.getFirstname(), request.getLastname());
        AuthorEntity savedAuthorEntity = service.addAuthor(newAuthorEntity);

        if (savedAuthorEntity == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {
            BeanUtils.copyProperties(savedAuthorEntity, newAuthorType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }
        response.setAuthorType(newAuthorType);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAuthorRequest")
    @ResponsePayload
    public UpdateAuthorResponse updateAuthor(@RequestPayload UpdateAuthorRequest request) {
        UpdateAuthorResponse response = new UpdateAuthorResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        // 1. Find if author available
        AuthorEntity authorFromDB = service.getAuthorById(request.getAuthorId());
        if(authorFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Author = " + request.getFirstname() + " not found");
        }else {
            // 2. Get updated author information from the request
            authorFromDB.setFirstname(request.getFirstname());
            authorFromDB.setLastname(request.getLastname());
            // 3. update the author in database
            boolean flag = service.updateAuthor(authorFromDB);
            if(flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getFirstname());;
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAuthorRequest")
    @ResponsePayload
    public DeleteAuthorResponse deleteAuthor(@RequestPayload DeleteAuthorRequest request) {
        DeleteAuthorResponse response = new DeleteAuthorResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        boolean flag = service.deleteAuthorById(request.getAuthorId());
        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deletint Entity id=" + request.getAuthorId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
