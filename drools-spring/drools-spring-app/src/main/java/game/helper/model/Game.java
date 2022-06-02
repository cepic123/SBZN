package game.helper.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import game.helper.model.enums.Genre;
@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="GENRE",
            joinColumns=@JoinColumn(name="GAME_ID")
    )
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, name = "genre")
	private List<Genre> genres;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studio_id", nullable = false)
	private DeveloperStudio studio;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private double lenght;
	@Column(nullable = false)
	private boolean isMultiplayer;
	@Column(nullable = false)
	private boolean isOnline;
	@OneToMany(mappedBy = "game")
	private List<Review> reviews = new ArrayList<>();
	
	public Game() {
		super();
	}

	public Game(String name, DeveloperStudio studio, double price, double lenght, boolean isMultiplayer,
			boolean isOnline) {
		super();
		this.name = name;
		this.studio = studio;
		this.price = price;
		this.lenght = lenght;
		this.isMultiplayer = isMultiplayer;
		this.isOnline = isOnline;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenre(List<Genre> genres) {
		this.genres = genres;
	}

	public DeveloperStudio getStudio() {
		return studio;
	}

	public void setStudio(DeveloperStudio studio) {
		this.studio = studio;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLenght() {
		return lenght;
	}

	public void setLenght(double lenght) {
		this.lenght = lenght;
	}

	public boolean isMultiplayer() {
		return isMultiplayer;
	}

	public void setMultiplayer(boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
