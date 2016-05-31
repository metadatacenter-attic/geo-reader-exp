package org.metadatacenter.readers.geo.metadata;

public class Contributor
{
  private final String name;
  private final String email;
  private final String phone;
  private final String fax;
  private final String laboratory;
  private final String department;
  private final String institute;
  private final String address;
  private final String city;
  private final String state;
  private final String zipOrPostalCode;
  private final String country;
  private final String webLink;

  public Contributor(String name, String email, String phone, String fax, String laboratory, String department,
    String institute, String address, String city, String state, String zipOrPostalCode, String country, String webLink)
  {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.fax = fax;
    this.laboratory = laboratory;
    this.department = department;
    this.institute = institute;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipOrPostalCode = zipOrPostalCode;
    this.country = country;
    this.webLink = webLink;
  }

  public Contributor(String name)
  {
    this.name = name;
    this.email = "";
    this.phone = "";
    this.fax = "";
    this.laboratory = "";
    this.department = "";
    this.institute = "";
    this.address = "";
    this.city = "";
    this.state = "";
    this.zipOrPostalCode = "";
    this.country = "";
    this.webLink = "";
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getFax()
  {
    return fax;
  }

  public String getLaboratory()
  {
    return laboratory;
  }

  public String getDepartment()
  {
    return department;
  }

  public String getInstitute()
  {
    return institute;
  }

  public String getAddress()
  {
    return address;
  }

  public String getCity()
  {
    return city;
  }

  public String getState()
  {
    return state;
  }

  public String getZipOrPostalCode()
  {
    return zipOrPostalCode;
  }

  public String getCountry()
  {
    return country;
  }

  public String getWebLink()
  {
    return webLink;
  }
}
