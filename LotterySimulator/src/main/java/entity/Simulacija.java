package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "simulacija")
public class Simulacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "hash")
	private String hash;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date simulationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "duration")
	private Integer duration;
	
	@Column(name = "type")
	private String lotteryType;
	
	@Column(name = "days")
	private String selectedDays;
	
	@Column(name = "numbers")
	private String selectedNumbers;
	
	@Column(name = "jackpot")
	private Boolean jackpot;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getSelectedDays() {
		return selectedDays;
	}

	public void setSelectedDays(String selectedDays) {
		this.selectedDays = selectedDays;
	}

	public String getSelectedNumbers() {
		return selectedNumbers;
	}

	public void setSelectedNumbers(String selectedNumbers) {
		this.selectedNumbers = selectedNumbers;
	}

	public Boolean getJackpot() {
		return jackpot;
	}

	public void setJackpot(Boolean jackpot) {
		this.jackpot = jackpot;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getSimulationDate() {
		return simulationDate;
	}

	public void setSimulationDate(Date simulationDate) {
		this.simulationDate = simulationDate;
	}
	
	

}
