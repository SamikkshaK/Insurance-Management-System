package dao;

import entity.Claim;
import java.util.List;

public interface IClaimDAO {
    boolean addClaim(Claim claim);
    boolean updateClaimStatus(int claimId, String status);
    Claim getClaimById(int claimId);
    List<Claim> getAllClaims();
}
