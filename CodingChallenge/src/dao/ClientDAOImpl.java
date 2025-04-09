package dao;

import entity.Client;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class ClientDAOImpl implements IClientDAO {
    private static final Connection connection = DBConnection.getConnection();

    @Override
    public boolean addClient(Client client) {
        String sql = "INSERT INTO clients (client_name, contact_info, policy_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getClientName());
            ps.setString(2, client.getContactInfo());
            ps.setInt(3, client.getPolicyId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in addClient: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateClient(Client client) {
        String sql = "UPDATE clients SET client_name = ?, contact_info = ?, policy_id = ? WHERE client_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getClientName());
            ps.setString(2, client.getContactInfo());
            ps.setInt(3, client.getPolicyId());
            ps.setInt(4, client.getClientId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in updateClient: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteClient(int clientId) {
        String sql = "DELETE FROM clients WHERE client_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in deleteClient: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Client getClientById(int clientId) {
        String sql = "SELECT * FROM clients WHERE client_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getInt("client_id"),
                        rs.getString("client_name"),
                        rs.getString("contact_info"),
                        rs.getInt("policy_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error in getClientById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("client_id"),
                        rs.getString("client_name"),
                        rs.getString("contact_info"),
                        rs.getInt("policy_id")));
            }
        } catch (SQLException e) {
            System.out.println("Error in getAllClients: " + e.getMessage());
        }
        return clients;
    }
}
