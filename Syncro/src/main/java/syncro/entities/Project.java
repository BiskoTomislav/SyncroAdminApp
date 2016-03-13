package syncro.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Projects")
public class Project {

	public enum ProjectSubtypes {
		PROJECT, PARTNERSHIP
	}
	
	@Id
	private String _id;

	public void set_id(String _id) {
		this._id = _id;
	}

	@Field("NOTES")
	private String notes = new String();
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Field("APPLICANT_ORGANIZATION")
	private String applicantOrganization = new String();
	
	@Field("SUBMISSION_OF_PROPOSAL")
	private String submissionDate = new String();
	
	@Field("SUBTYPE")
	private String subtype = new String();

	@Field("PROJECT_TIMEFRAME")
	private ProjectTimeFrame timeframe = new ProjectTimeFrame();
	
	@Field("MAIN_DATA")
	private MainData data = new MainData();

	@Field("FINANCING_PROGRAM")
	private FinancingProgram financingProgram = new FinancingProgram();

	@Field("PARTNERS")
	private List<String> partnerIds;
	
	private static List<String> projectStatusOptions = new ArrayList<>();
	private static List<String> projectTypeOptions = new ArrayList<>();
	
	static {
		projectStatusOptions.add("no status");
		projectStatusOptions.add("Submitted");
		projectStatusOptions.add("In implementation ");
		projectStatusOptions.add("Rejected");
		projectStatusOptions.add("Closed ");	
		
		projectTypeOptions.add("no status");
		projectTypeOptions.add("Youth exchange");
		projectTypeOptions.add("Training");
	}

	public static List<String> getProjectStatusOptions() {
		return projectStatusOptions;
	}

	public static void setProjectStatusOptions(List<String> projectStatusOptions) {
		Project.projectStatusOptions = projectStatusOptions;
	}

	public Project() {
		this.data = new MainData();
		this.financingProgram = new FinancingProgram();
		this.financingProgram.contactPerson = new Project.FinancingProgram.ContactData();
	}

	public Project(String _id, 
			MainData data,
			FinancingProgram financingProgram, 
			List<String> partnerIds) {
		super();
		this._id = _id;
		this.data = data;
		this.financingProgram = financingProgram;
		this.partnerIds = partnerIds;
	}


	public MainData getData() {
		return data;
	}

	public void setData(MainData data) {
		this.data = data;
	}

	public FinancingProgram getFinancingProgram() {
		return financingProgram;
	}

	public void setFinancingProgram(FinancingProgram financingProgram) {
		this.financingProgram = financingProgram;
	}
	
	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public ProjectTimeFrame getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(ProjectTimeFrame timeframe) {
		this.timeframe = timeframe;
	}

	public String getApplicantOrganization() {
		return applicantOrganization;
	}

	public void setApplicantOrganization(String applicantOrganization) {
		this.applicantOrganization = applicantOrganization;
	}
	public List<String> getPartnerIds() {
		return partnerIds;
	}

	public void setPartnerIds(List<String> partnerIds) {
		this.partnerIds = partnerIds;
	}

	public String get_id() {
		return _id;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	@Override
	public String toString() {
		return "Project [_id=" + _id + ", data=" + data + ", financingProgram="
				+ financingProgram + ", partnerIds=" + partnerIds + "]";
	}
	
	public static List<String> getProjectTypeOptions() {
		return projectTypeOptions;
	}

	public static void setProjectTypeOptions(List<String> projectTypeOptions) {
		Project.projectTypeOptions = projectTypeOptions;
	}

	public static class MainData {
		@Field("STATUS")
		private String status;
		@Field("PROJECT_NAME")
		private String name;
		@Field("TYPE")
		private String type;
		@Field("SUBJECT")
		private String subject;
		@Field("DURATION")
		private String duration;
		@Field("ACTIVITY_DURATION")
		private String activity;
		@Field("PLACE")
		private String place;
		@Field("ORGANIZER")
		private String organizer;

		public MainData() {
		}

		public MainData(String status, String name, String type,
				String subject, String duration, String activity, String place,
				String organizer) {
			super();
			this.status = status;
			this.name = name;
			this.type = type;
			this.subject = subject;
			this.duration = duration;
			this.activity = activity;
			this.place = place;
			this.organizer = organizer;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getActivity() {
			return activity;
		}

		public void setActivity(String activity) {
			this.activity = activity;
		}

		public String getPlace() {
			return place;
		}

		public void setPlace(String place) {
			this.place = place;
		}

		public String getOrganizer() {
			return organizer;
		}

		public void setOrganizer(String organizer) {
			this.organizer = organizer;
		}

		@Override
		public String toString() {
			return "MainData [status=" + status + ", name=" + name + ", type="
					+ type + ", subject=" + subject + ", duration=" + duration
					+ ", activity=" + activity + ", place=" + place
					+ ", organizer=" + organizer + "]";
		}

	}

	public static class ProjectTimeFrame {
		private String projectStartDate;
		private String projectEndDate;
		
		private String activityStartDate;
		private String activityEndDate;
		
		private double duration;

		
		public ProjectTimeFrame() {

		}
		
		public ProjectTimeFrame(String projectStartDate, String projectEndDate,
				String activityStartDate, String activityEndDate, double duration) {
			super();
			this.projectStartDate = projectStartDate;
			this.projectEndDate = projectEndDate;
			this.activityStartDate = activityStartDate;
			this.activityEndDate = activityEndDate;
			this.duration = duration;
		}

		@Override
		public String toString() {
			return "ProjectTimeFrame [projectStartDate=" + projectStartDate
					+ ", projectEndDate=" + projectEndDate + ", activityStart="
					+ activityStartDate + ", activityEnd=" + activityEndDate
					+ ", duration=" + duration + "]";
		}

		public String getProjectStartDate() {
			return projectStartDate;
		}

		public void setProjectStartDate(String projectStartDate) {
			this.projectStartDate = projectStartDate;
		}

		public String getProjectEndDate() {
			return projectEndDate;
		}

		public void setProjectEndDate(String projectEndDate) {
			this.projectEndDate = projectEndDate;
		}

		public String getActivityStartDate() {
			return activityStartDate;
		}

		public void setActivityStartDate(String activityStartDate) {
			this.activityStartDate = activityStartDate;
		}

		public String getActivityEndDate() {
			return activityEndDate;
		}

		public void setActivityEndDate(String activityEndDate) {
			this.activityEndDate = activityEndDate;
		}

		public double getDuration() {
			return duration;
		}

		public void setDuration(double duration) {
			this.duration = duration;
		}
		
		
	}
	
	public static class FinancingProgram {

		@Field("PROGRAM")
		private String programName;
		@Field("ADMINISTARTIVE_BODY")
		private String administrativeBody;
		
		@Field("GRANT")
		private String grant;
		
		@Field("CO_FINANCING_KN")
		private String coFinancingKN;
		@Field("CO_FINANCING_EU")
		private String coFinancingEU;
		@Field("VALUE")
		private String value;
		@Field("DATE_OF_VALUE")
		private String dateOfValue;
		@Field("SPONSOR")
		private String sponsor;
		@Field("REF_ID")
		private String refId;
		@Field("DUE")
		private String due;
		@Field("VENUE")
		private String venue;
		@Field("CONTACT")
		private ContactData contactPerson = new ContactData();

		public FinancingProgram() {
		}

		public FinancingProgram(String programName, String sponsor,
				String refId, String due, ContactData contactPerson) {
			super();
			this.programName = programName;
			this.sponsor = sponsor;
			this.refId = refId;
			this.due = due;
			this.contactPerson = contactPerson;
		}

		public ContactData getContactPerson() {
			return contactPerson;
		}

		public void setContactPerson(ContactData contactPerson) {
			this.contactPerson = contactPerson;
		}
		
		public String getAdministrativeBody() {
			return administrativeBody;
		}

		public void setAdministrativeBody(String administrativeBody) {
			this.administrativeBody = administrativeBody;
		}

		public String getGrant() {
			return grant;
		}

		public void setGrant(String grant) {
			this.grant = grant;
		}

		public String getCoFinancingKN() {
			return coFinancingKN;
		}

		public void setCoFinancingKN(String coFinancingKN) {
			this.coFinancingKN = coFinancingKN;
		}

		public String getCoFinancingEU() {
			return coFinancingEU;
		}

		public void setCoFinancingEU(String coFinancingEU) {
			this.coFinancingEU = coFinancingEU;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getDateOfValue() {
			return dateOfValue;
		}

		public void setDateOfValue(String dateOfValue) {
			this.dateOfValue = dateOfValue;
		}

		public String getVenue() {
			return venue;
		}

		public void setVenue(String venue) {
			this.venue = venue;
		}
		
		public String getProgramName() {
			return programName;
		}

		public void setProgramName(String programName) {
			this.programName = programName;
		}



		public String getSponsor() {
			return sponsor;
		}



		public void setSponsor(String sponsor) {
			this.sponsor = sponsor;
		}



		public String getRefId() {
			return refId;
		}



		public void setRefId(String refId) {
			this.refId = refId;
		}



		public String getDue() {
			return due;
		}



		public void setDue(String due) {
			this.due = due;
		}

		@Override
		public String toString() {
			return "FinancingProgram [programName=" + programName
					+ ", sponsor=" + sponsor + ", refId=" + refId + ", due="
					+ due + ", contactPerson=" + contactPerson + "]";
		}
		
		public static class ContactData {
			@Field("CONTACT")
			private String personName;
			@Field("ROLE")
			private String personRole;
			@Field("EMAIL")
			private String personEmail;
			@Field("PHONE")
			private String personPhone;

			public ContactData() {
			}

			public ContactData(String personName, String personRole,
					String personEmail, String personPhone) {
				super();
				this.personName = personName;
				this.personRole = personRole;
				this.personEmail = personEmail;
				this.personPhone = personPhone;
			}

			public String getPersonName() {
				return personName;
			}

			public void setPersonName(String personName) {
				this.personName = personName;
			}

			public String getPersonRole() {
				return personRole;
			}

			public void setPersonRole(String personRole) {
				this.personRole = personRole;
			}

			public String getPersonEmail() {
				return personEmail;
			}

			public void setPersonEmail(String personEmail) {
				this.personEmail = personEmail;
			}

			public String getPersonPhone() {
				return personPhone;
			}

			public void setPersonPhone(String personPhone) {
				this.personPhone = personPhone;
			}
			

			@Override
			public String toString() {
				return "ContactData [personName=" + personName
						+ ", personRole=" + personRole + ", personEmail="
						+ personEmail + ", personPhone=" + personPhone + "]";
			}
		}
	}

}
