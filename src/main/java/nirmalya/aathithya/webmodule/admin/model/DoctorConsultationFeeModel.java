package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorConsultationFeeModel {

	
	private String conSlNo;
	private String docAudio;
	private String docVideo;
	private String docPhy;
		
	public DoctorConsultationFeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getConSlNo() {
		return conSlNo;
	}

	public void setConSlNo(String conSlNo) {
		this.conSlNo = conSlNo;
	}

	public String getDocAudio() {
		return docAudio;
	}

	public void setDocAudio(String docAudio) {
		this.docAudio = docAudio;
	}

	public String getDocVideo() {
		return docVideo;
	}

	public void setDocVideo(String docVideo) {
		this.docVideo = docVideo;
	}

	public String getDocPhy() {
		return docPhy;
	}

	public void setDocPhy(String docPhy) {
		this.docPhy = docPhy;
	}
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
	
}
