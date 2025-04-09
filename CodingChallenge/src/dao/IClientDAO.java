package dao;

import entity.Client;
import java.util.List;

public interface IClientDAO {
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean deleteClient(int clientId);
    Client getClientById(int clientId);
    List<Client> getAllClients();
}
