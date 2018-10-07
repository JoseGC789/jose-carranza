package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.NotFoundException;
import com.globant.bootcamp.dia15.UnauthorizedException;
import com.globant.bootcamp.dia15.component.ClientCredentials;
import com.globant.bootcamp.dia15.domain.entity.Client;
import com.globant.bootcamp.dia15.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginClientService {
    @Autowired
    private ClientRepository clients;

    public Client loginClient(ClientCredentials clientCredentials){
        Client clientFromDB = clients.findByUsername(clientCredentials.getUsername());
        if (clientFromDB == null){
            throw new NotFoundException();
        }else{
            if (!clientFromDB.getPassword().equals(clientCredentials.getPassword())){
                throw new UnauthorizedException();
            }
        }
        return clientFromDB;
    }
}
