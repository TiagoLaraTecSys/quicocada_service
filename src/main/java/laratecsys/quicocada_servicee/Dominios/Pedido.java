package laratecsys.quicocada_servicee.Dominios;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yy HH:mm")
	private Date instantDate;
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "enderecoEntrega_id")
	private Endereco enderecoEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Pedido() {
		
	}
	public Pedido(Integer id, Date instantDate, Cliente cliente, Endereco enderecoEntrega) {
		super();
		this.id = id;
		this.instantDate = instantDate;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public Pedido(Integer id, Date instantDate, Pagamento pagamento, Cliente cliente, Endereco enderecoEntrega) {
		super();
		this.id = id;
		this.instantDate = instantDate;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public double getValorTotal() {
		double soma = 0.0;
		
		for(ItemPedido itp : itens) {
			
			soma = soma + itp.getSubtotal();
		}
		return soma;
			
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstantDate() {
		return instantDate;
	}

	public void setInstantDate(Date instantDate) {
		this.instantDate = instantDate;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido Numero: ");
		builder.append(getId());
		builder.append("Data do pedido: ");
		builder.append(sd.format(getInstantDate()));
		builder.append(", cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do Pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes: \n");
		
		for (ItemPedido itemPedido : getItens()) {
			builder.append(itemPedido.toString());
			
		}
		
		builder.append("\nValor Total: R$ ");
		builder.append(nf.format(getValorTotal()));
		
		return builder.toString();
	}


	
}
