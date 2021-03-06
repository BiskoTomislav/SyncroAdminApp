package syncro.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Contacts")
public class Contact {

	@Id
    private String _id;
	
	public void set_id(String _id) {
		this._id = _id;
	}

	@Field("MAIN_DATA")
	private MainData data = new MainData();
	public MainData getData() {
		return data;
	}

	public void setData(MainData data) {
		this.data = data;
	}

	@Field("CONTACT")
	private ContactData contactPerson = new ContactData();
	public ContactData getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactData contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Field("INVOLVED_PROJECTS")
	private List<String> projectIds;
	
    public Contact() {}
    
	public Contact(String _id, MainData data, ContactData contactPerson,
			List<String> projectIds) {
		super();
		this._id = _id;
		this.data = data;
		this.contactPerson = contactPerson;
		this.projectIds = projectIds;
	}

	public String getCompanyName() {
		return data.getCompanyName();
	}

	public void setCompanyName(String companyName) {
		this.data.setCompanyName(companyName);
	}

	public String getCountry() {
		return data.getCountry();
	}

	public void setCountry(String country) {
		this.data.setCountry(country);
	}

	public String getAddress() {
		return data.getAddress();
	}

	public void setAddress(String address) {
		this.data.setAddress(address);
	}

	public String getEmail() {
		return data.getEmail();
	}

	public void setEmail(String email) {
		this.data.setEmail(email);
	}

	public String getPhone() {
		return data.getPhone();
	}

	public void setPhone(String phone) {
		this.data.setPhone(phone);
	}

	public String getWebSite() {
		return data.getWebSite();
	}

	public void setWebSite(String webSite) {
		this.data.setWebSite(webSite);
	}

	public String getFacebook() {
		return data.getFacebook();
	}

	public void setFacebook(String facebook) {
		this.data.setFacebook(facebook);
	}

	public List<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}

	public String getPersonName() {
		return contactPerson.getPersonName();
	}


	public void setPersonName(String personName) {
		this.contactPerson.setPersonName(personName);
	}


	public String getPersonRole() {
		return contactPerson.getPersonRole();
	}


	public void setPersonRole(String personRole) {
		this.contactPerson.setPersonRole(personRole);
	}


	public String getPersonEmail() {
		return contactPerson.getPersonEmail();
	}


	public void setPersonEmail(String personEmail) {
		this.contactPerson.setPersonEmail(personEmail);
	}


	public String getPersonPhone() {
		return contactPerson.getPersonPhone();
	}


	public void setPersonPhone(String personPhone) {
		this.contactPerson.setPersonPhone(personPhone);
	}

	public String get_id() {
		return _id;
	}
	
	@Override
	public String toString() {
		return "Contact [_id=" + _id + ", data=" + data + ", contactPerson="
				+ contactPerson + ", projectIds=" + projectIds + "]";
	}

	public static class MainData {
		@Field("NAME")
		private String companyName;
		@Field("COUNTRY")
		private String country;
		@Field("ADDRESS")
		private String address;
		@Field("EMAIL")
		private String email;
		@Field("PHONE")
		private String phone;
		@Field("WEB_SITE")
		private String webSite;
		@Field("FACEBOOK")
		private String facebook;

		public MainData() {
		}

		public MainData(String companyName, String country, String address,
				String email, String phone, String webSite, String facebook) {
			super();
			this.companyName = companyName;
			this.country = country;
			this.address = address;
			this.email = email;
			this.phone = phone;
			this.webSite = webSite;
			this.facebook = facebook;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getWebSite() {
			return webSite;
		}

		public void setWebSite(String webSite) {
			this.webSite = webSite;
		}

		public String getFacebook() {
			return facebook;
		}

		public void setFacebook(String facebook) {
			this.facebook = facebook;
		}

		@Override
		public String toString() {
			return "MainData [companyName=" + companyName + ", country="
					+ country + ", address=" + address + ", email=" + email
					+ ", phone=" + phone + ", webSite=" + webSite
					+ ", facebook=" + facebook + "]";
		}
		
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
			return "ContactData [personName=" + personName + ", personRole="
					+ personRole + ", personEmail=" + personEmail
					+ ", personPhone=" + personPhone + "]";
		}
		
	}

}
