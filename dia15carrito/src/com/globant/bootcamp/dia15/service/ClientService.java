package com.globant.bootcamp.dia15.service;


import com.globant.bootcamp.dia15.NotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Client;
import com.globant.bootcamp.dia15.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clients;

    public List<Client> getClients(){
        return clients.findAll();
    }

    public Client getClient(Integer id){
        clients.findById(id)
                .orElseThrow(() -> new NotFoundException());
        return clients.getOne(id);

    }

    public Client createClient(Client client){
        Calendar calendar = new GregorianCalendar();
        client.setDateJoined(calendar);
        client.setLastSeen(calendar);
        return clients.save(client);
    }

    public Client updateClient(Client client){
        clients.findById(client.getId())
                .orElseThrow(() -> new NotFoundException());
        return clients.save(client);
    }

    public Client deleteClient(Integer id){
        clients.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Client clientFromDB = clients.getOne(id);
        clients.delete(clientFromDB);
        return clientFromDB;
    }
}
