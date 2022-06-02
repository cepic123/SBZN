package game.helper.model.dto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import game.helper.model.enums.RuleStatus;

public class ListGameResultDTO {
	private List<GameResultDTO> list;
	private RuleStatus status;
	
	public ListGameResultDTO(List<GameResultDTO> list) {
		super();
		this.list = list;
	}

	public ListGameResultDTO() {
		super();
	}

	public List<GameResultDTO> getList() {
		return list;
	}

	public void setList(List<GameResultDTO> list) {
		this.list = list;
	}
	
	public void sortIt() {
		Collections.sort(list, Comparator.comparingDouble(GameResultDTO ::getPoints).reversed());
	}
	
	public void updateRankings() {
		for(int i=0; i<list.size();i++) {
			list.get(i).setRank(i+1);
		}
	}

	public RuleStatus getStatus() {
		return status;
	}

	public void setStatus(RuleStatus status) {
		this.status = status;
	}
	
}
