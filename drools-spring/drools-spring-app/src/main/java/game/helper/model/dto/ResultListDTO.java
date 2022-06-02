package game.helper.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultListDTO {
	private List<CombiningDTO> list;
	private List<CombiningDTO> resultList = new ArrayList<>();
	
	public ResultListDTO(List<CombiningDTO> list) {
		super();
		this.list = list;
	}

	public ResultListDTO() {
		super();
	}

	public List<CombiningDTO> getList() {
		return list;
	}

	public void setList(List<CombiningDTO> list) {
		this.list = list;
	}

	public List<CombiningDTO> getResultList() {
		return resultList;
	}

	public void setResultList(List<CombiningDTO> resultList) {
		this.resultList = resultList;
	}
	
	public void addToResult(CombiningDTO g) {
		this.resultList.add(g);
	}
	
}
