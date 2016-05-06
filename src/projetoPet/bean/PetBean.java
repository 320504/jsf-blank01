package projetoPet.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import projetoPet.dao.PetDAO;
import projetoPet.dto.Pet;


@ManagedBean
@SessionScoped
public class PetBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117522035181555602L;
	private String nome;
	private String telefone;
	private String endereco;
	private String nomePet;
	private String tipoPet;
	private int idadePet;
    private PetDAO petDAO = new PetDAO();
	private ArrayList<Pet> listaPet = new ArrayList<Pet>();
	private boolean exibirTabela = false;
	private Pet petAlteracao = new Pet();
		
	public Pet getPetAlteracao() {
		return petAlteracao;
	}
	public void setPetAlteracao(Pet petAlteracao) {
		this.petAlteracao = petAlteracao;
	}
	public boolean isExibirTabela() {
		return exibirTabela;
	}
	public void setExibirTabela(boolean exibirTabela) {
		this.exibirTabela = exibirTabela;
	}
	public PetDAO getPetDAO() {
		return petDAO;
	}
	public void setPetDAO(PetDAO petDAO) {
		this.petDAO = petDAO;
	}
	public ArrayList<Pet> getListaPet() {
		return listaPet;
	}
	public void setListaPet(ArrayList<Pet> listaPet) {
		this.listaPet = listaPet;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNomePet() {
		return nomePet;
	}
	public void setNomePet(String nomePet) {
		this.nomePet = nomePet;
	}
	public String getTipoPet() {
		return tipoPet;
	}
	public void setTipoPet(String tipoPet) {
		this.tipoPet = tipoPet;
	}
	public int getIdadePet() {
		return idadePet;
	}
	public void setIdadePet(int idadePet) {
		this.idadePet = idadePet;
	}
	
	public void cadastrar(){
		Pet pet = new Pet();
		pet.setNome(nome);
		pet.setTelefone(telefone);
		pet.setEndereco(endereco);
		pet.setNomePet(nomePet);
		pet.setTipoPet(tipoPet);
		pet.setIdadePet(idadePet);
		petDAO.inserir(pet);
		limparForm();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro inserido com sucesso.", null));
	}
	
	
	public void consultar(){
	
		Pet pet = new Pet();
		pet.setNome(nome);
		pet.setNomePet(nomePet);
		pet.setTipoPet(tipoPet);
		listaPet = (ArrayList<Pet>)petDAO.consultar(pet);
		exibirTabela = true;
	
	}
	
	
	public String setPetParaAlterar(Pet pet){
		
		this.petAlteracao = pet;
		return "editarCadastro"; 
		
	}
	
	public String alterar(){
		
		
		petDAO.atualizar(petAlteracao);
		limparForm();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro alterado com sucesso.", null));
		return "pesquisar";
		
		
	}
	
	public void limparForm(){
		listaPet = new ArrayList<Pet>();
		nome = null;
		telefone = null;
		endereco = null;
		nomePet = null;
		tipoPet = null;
		idadePet = 0;
		exibirTabela = false;
		petAlteracao = new Pet();
	}
	

		
	public void excluir(Pet pet){
		petDAO.deletar(pet.getId());
		listaPet.remove(pet);
		
	}
	
	
	
	public String menuCadastrar(){
		limparForm();
		return "novoCadastro";
	}
	
	
	
		
		
		
	}

	
	
	
