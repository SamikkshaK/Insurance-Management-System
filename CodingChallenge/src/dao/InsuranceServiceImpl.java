package dao;

import dao.*;
import entity.*;

import java.util.List;

public class InsuranceServiceImpl {

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IPolicyDAO policyDAO = new PolicyDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final IClaimDAO claimDAO = new ClaimDAOImpl();
    private final IPaymentDAO paymentDAO = new PaymentDAOImpl();

   
    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    public User loginUser(String username, String password) {
        return userDAO.loginUser(username, password);
    }

   
    public boolean addPolicy(Policy policy) {
        return policyDAO.addPolicy(policy);
    }

    public boolean updatePolicy(Policy policy) {
        return policyDAO.updatePolicy(policy);
    }

    public boolean deletePolicy(int policyId) {
        return policyDAO.deletePolicy(policyId);
    }

    public Policy getPolicyById(int policyId) {
        return policyDAO.getPolicyById(policyId);
    }

    public List<Policy> getAllPolicies() {
        return policyDAO.getAllPolicies();
    }

   
    public boolean addClient(Client client) {
        return clientDAO.addClient(client);
    }

    public boolean updateClient(Client client) {
        return clientDAO.updateClient(client);
    }

    public boolean deleteClient(int clientId) {
        return clientDAO.deleteClient(clientId);
    }

    public Client getClientById(int clientId) {
        return clientDAO.getClientById(clientId);
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    
    public boolean addClaim(Claim claim) {
        return claimDAO.addClaim(claim);
    }

    public boolean updateClaimStatus(int claimId, String status) {
        return claimDAO.updateClaimStatus(claimId, status);
    }

    public Claim getClaimById(int claimId) {
        return claimDAO.getClaimById(claimId);
    }

    public List<Claim> getAllClaims() {
        return claimDAO.getAllClaims();
    }

    
    public boolean addPayment(Payment payment) {
        return paymentDAO.addPayment(payment);
    }

    public List<Payment> getPaymentsByClientId(int clientId) {
        return paymentDAO.getPaymentsByClientId(clientId);
    }
}
