package syncro.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Users")
public class User {

	@Id
    private String _id;
	@Field("USER_ID")
	private String userId;
	
	@Field
	private String profileImage = "data:image/gif;base64,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";

	@Field("MAIN_INFO")
	private MainInfo data = new MainInfo();
	@Field("HOME_ADDRESS")
	private Address homeAddress = new Address();
	@Field("LIVING_ADDRESS")
	private Address livingAddress = new Address();

	@Field("WORK_IN_SYNCRO")
	private WorkInSyncro workInSyncro = new WorkInSyncro();
	@Field("EDUCATION")
	private Education education = new Education();
	
	@Field
	private List<ProjectInfo> projects = new ArrayList<>();

	private static List<String> userStatusOptions = new ArrayList<>();
	
	static {
		userStatusOptions.add("no status");
		userStatusOptions.add("member");
		userStatusOptions.add("employee");
		userStatusOptions.add("associate");
		userStatusOptions.add("volunteer");	
		userStatusOptions.add("EVS volunteer");
		userStatusOptions.add("member of honour");
		userStatusOptions.add("user");
	}

	public static List<String> getUserStatusOptions() {
		return userStatusOptions;
	}

	public User() {
		this.data = new MainInfo();
		this.homeAddress = new Address();
		this.livingAddress = new Address();
	}
    
	public User(String _id, MainInfo data, Address homeAddress,
			Address livingAddress, WorkInSyncro workInSyncro,
			Education education) {
		super();
		this._id = _id;
		this.data = data;
		this.homeAddress = homeAddress;
		this.livingAddress = livingAddress;
		this.workInSyncro = workInSyncro;
		this.education = education;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void set_id(String _id) {
		this._id = _id; 
	}
	
	public String get_id() {
		return _id;
	}

	public MainInfo getData() {
		return data;
	}

	public void setData(MainInfo data) {
		this.data = data;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(Address livingAddress) {
		this.livingAddress = livingAddress;
	}

	public WorkInSyncro getWorkInSyncro() {
		return workInSyncro;
	}

	public void setWorkInSyncro(WorkInSyncro workInSyncro) {
		this.workInSyncro = workInSyncro;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}
	
	public List<ProjectInfo> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectInfo> projects) {
		this.projects = projects;
	}

	
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	@Override
	public String toString() {
		return "User [_id=" + _id + ", data=" + data + ", homeAddress="
				+ homeAddress + ", livingAddress=" + livingAddress
				+ ", workInSyncro=" + workInSyncro + ", education=" + education
				+ "]";
	}

	public static class MainInfo {
		@Field("NAME")
		private String name;
		@Field("SURNAME")
		private String surname;
		@Field("FOTO")
		private String foto;

		@Field("YOB")
		private String yob;
		@Field("DOB")
		private String dob;
		
		@Field("OIB")
		private String oib;
		
		@Field("EMAIL")
		private String email;
		@Field("MOBILE")
		private String mobile;
		
		@Field("STATUS")
		private String status;
		@Field("NATIONALITY")
		private String nationality;
		@Field("CITIZENSHIP")
		private String citizenship;
		@Field("SEX")
		private String sex;

		public MainInfo() {
		}

		public MainInfo(String name, String surname, String foto, String yob,
				String dob, String oib, String email, String mobile,
				String status, String nationality, String citizenship,
				String sex) {
			super();
			this.name = name;
			this.surname = surname;
			this.foto = foto;
			this.yob = yob;
			this.dob = dob;
			this.oib = oib;
			this.email = email;
			this.mobile = mobile;
			this.status = status;
			this.nationality = nationality;
			this.citizenship = citizenship;
			this.sex = sex;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public String getYob() {
			return yob;
		}

		public void setYob(String yob) {
			this.yob = yob;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getOib() {
			return oib;
		}

		public void setOib(String oib) {
			this.oib = oib;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getCitizenship() {
			return citizenship;
		}

		public void setCitizenship(String citizenship) {
			this.citizenship = citizenship;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		@Override
		public String toString() {
			return "MainData [name=" + name + ", surname=" + surname
					+ ", foto=" + foto + ", yob=" + yob + ", dob=" + dob
					+ ", oib=" + oib + ", email=" + email + ", mobile="
					+ mobile + ", status=" + status + ", nationality="
					+ nationality + ", citizenship=" + citizenship + ", sex="
					+ sex + "]";
		}
	}
	
	public static class Address {
		
		@Field("ADDRESS")
		private String address;
		@Field("CITY")
		private String city;
		@Field("POST_NUM")
		private String postNum;

		public Address() {
		}

		public Address(String address, String city, String postNum) {
			super();
			this.address = address;
			this.city = city;
			this.postNum = postNum;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPostNum() {
			return postNum;
		}

		public void setPostNum(String postNum) {
			this.postNum = postNum;
		}

		@Override
		public String toString() {
			return "Address [address=" + address + ", personRole="
					+ city + ", city=" + postNum + "]";
		}

	}
	
	public static class WorkInSyncro {
		
		@Field("STATUS")
		private String status;
		@Field("PROJECT")
		private String project;
		@Field("START_DATE")
		private String startDate;
		@Field("END_DATE")
		private String endDate;
		@Field("ADDITIONAL_INFO")
		private String additionalInfo;
		
		public WorkInSyncro() {
		}

		public WorkInSyncro(String status, String project, String startDate,
				String endDate, String additionalInfo) {
			super();
			this.status = status;
			this.project = project;
			this.startDate = startDate;
			this.endDate = endDate;
			this.additionalInfo = additionalInfo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getAdditionalInfo() {
			return additionalInfo;
		}

		public void setAdditionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
		}

		@Override
		public String toString() {
			return "WorkInSyncro [status=" + status + ", project=" + project
					+ ", startDate=" + startDate + ", endDate=" + endDate
					+ ", additionalInfo=" + additionalInfo + "]";
		}
		
	}
	
	public static class Education {
		
		@Field("STATUS")
		private String status;
		@Field("PROJECT")
		private String project;
		@Field("PROJECT_LEAD")
		private String startDate;
		@Field("TYPE")
		private String type;
		@Field("SUBJECT")
		private String subject;
		@Field("REFERENT_NUMBER")
		private String referentNumber;
		@Field("DURATION")
		private String endDate;
		@Field("PROJECT_ACTIVITY_DURATION")
		private String projectActivityDuration;
		@Field("ADDITIONAL_INFO")
		private String additionalInfo;
		
		public Education() {
		}
		
		public Education(String status, String project, String startDate,
				String type, String subject, String referentNumber,
				String endDate, String projectActivityDuration,
				String additionalInfo) {
			super();
			this.status = status;
			this.project = project;
			this.startDate = startDate;
			this.type = type;
			this.subject = subject;
			this.referentNumber = referentNumber;
			this.endDate = endDate;
			this.projectActivityDuration = projectActivityDuration;
			this.additionalInfo = additionalInfo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getReferentNumber() {
			return referentNumber;
		}

		public void setReferentNumber(String referentNumber) {
			this.referentNumber = referentNumber;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getProjectActivityDuration() {
			return projectActivityDuration;
		}

		public void setProjectActivityDuration(String projectActivityDuration) {
			this.projectActivityDuration = projectActivityDuration;
		}

		public String getAdditionalInfo() {
			return additionalInfo;
		}

		public void setAdditionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
		}

		@Override
		public String toString() {
			return "Education [status=" + status + ", project=" + project
					+ ", startDate=" + startDate + ", type=" + type
					+ ", subject=" + subject + ", referentNumber="
					+ referentNumber + ", endDate=" + endDate
					+ ", projectActivityDuration=" + projectActivityDuration
					+ ", additionalInfo=" + additionalInfo + "]";
		}
	}
	
	public static class ProjectInfo {
		
		@Field("STATUS")
		private String status;
		@Field("ADDITIONAL_INFO")
		private String info;
		@DBRef(db="syncro")
		private Project project; 
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String napomena) {
			this.info = napomena;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			
			if(! (obj instanceof ProjectInfo) ) return false;
			
			ProjectInfo pi = (ProjectInfo) obj;
			
			if(pi.getProject().get_id().equals(this.getProject().get_id())) return true;
			
			return false;
		}

		@Override
		public int hashCode() {
			int result = 17;
			
			result = 31 * this.getProject().get_id().hashCode() + result;
			
			return result;
		}
		
		
	} 
}
