package projetoPet.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projetoPet.dto.Pet;







public class PetDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
    ResultSet rs = null;


    public PetDAO () {}


private Connection getConnection() throws SQLException {
    Connection conn;
    conn = ConnectionFactory.getConnection();
    return conn;
}

public boolean inserir(Pet pet ) {
    try {
    	
        String queryString = "INSERT INTO pet("
        		+"id, nome, telefone, endereco, nome_pet, "
        		+ "tipo_pet, idade_pet) "
        		+ "VALUES (nextval('pet_seq'), ?, ?, ?,  "
        		+ "?, ?, ?)";
        	
        
        		
        connection = getConnection();
        ptmt = connection.prepareStatement(queryString);

            ptmt.setString(1,pet.getNome());
            ptmt.setString(2,pet.getTelefone());
            ptmt.setString(3,pet.getEndereco());
            ptmt.setString(4,pet.getNomePet());
            ptmt.setString(5,pet.getTipoPet());
            ptmt.setInt(6,pet.getIdadePet());
            
            
            
            
           
        ptmt.executeUpdate();
    } catch (SQLException e) {
    	e.printStackTrace();
    } finally {
        try {
            if (ptmt != null)
                ptmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	return true;
}

public void atualizar(Pet pet) {
	
    try {
        String queryString = "UPDATE pet SET nome = ?, telefone = ?, endereco = ?, nome_pet = ?, tipo_pet = ?, idade_pet = ? WHERE id=?";
        connection = getConnection();
        
        
        ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, pet.getNome());
            ptmt.setString(2, pet.getTelefone());
            ptmt.setString(3, pet.getEndereco());
            ptmt.setString(4, pet.getNomePet());
            ptmt.setString(5, pet.getTipoPet());
            ptmt.setInt(6, pet.getIdadePet());
            ptmt.setInt(7, pet.getId());
            
            
        ptmt.executeUpdate();
    } catch (SQLException e) {
    e.printStackTrace();
    } finally {
        try {
            if (ptmt != null)
            ptmt.close();
            if (connection != null)
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}

public boolean deletar(int idPet) {
    try {
        String queryString = "DELETE FROM pet WHERE id=?";
        connection = getConnection();
        ptmt = connection.prepareStatement(queryString);
        ptmt.setInt(1, idPet);
        ptmt.executeUpdate();
    } catch (SQLException e) {
    	e.printStackTrace();
    } finally {
        try {
            if (ptmt != null)
                    ptmt.close();
            if (connection != null)
                    connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	return true;
}


public ArrayList<Pet> consultar(Pet pet){ 
	ResultSet rs = null;
	PreparedStatement ptmt = null;
	ArrayList<Pet> listaPet = new ArrayList<Pet>();
	
    
	try {
        String queryString =  "SELECT * FROM pet WHERE 1 = 1 ";
	     if(pet.getNome() != null){
   			 queryString = queryString + " AND nome like '%"  + pet.getNome() +"%' ";
   		 }
	     
	     if(pet.getTelefone() != null){
   			 queryString = queryString + " AND telefone like '%"  + pet.getTelefone() + "%' ";
   		 } 

	     if(pet.getEndereco() != null){
   			 queryString = queryString + " AND endereco like '%"  + pet.getEndereco() + "%' ";
   		 } 
	     if(pet.getNomePet() != null){
   			 queryString = queryString + " AND nome_pet like '%"  + pet.getNomePet() + "%' ";
   		 } 
	     if(pet.getTipoPet() != null){
   			 queryString = queryString + " AND tipo_pet like '%"  + pet.getTipoPet() + "%' ";
   		 } 
	     if(pet.getIdadePet() > 0){
			 queryString = queryString + " AND idade_pet = "  + pet.getIdadePet() + " ";
		 }
			     
			  
                 
                 queryString = queryString +  " Order by nome ";
    connection = getConnection();
    System.out.println(queryString);
    ptmt = connection.prepareStatement(queryString);
    rs = ptmt.executeQuery();
    
    while (rs.next()) {
    	Pet objPet = new Pet();
    	objPet.setId(rs.getInt(1));
    	objPet.setNome(rs.getString(2));
    	objPet.setTelefone(rs.getString(3));
    	objPet.setEndereco(rs.getString(4));
    	objPet.setNomePet(rs.getString(5));
    	objPet.setTipoPet(rs.getString(6));
    	objPet.setIdadePet(rs.getInt(7));
    	
    	listaPet.add(objPet);
    }
	} catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	try {
            if (rs != null)
                rs.close();
            if (ptmt != null)
            	ptmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    return listaPet;
    }

}


