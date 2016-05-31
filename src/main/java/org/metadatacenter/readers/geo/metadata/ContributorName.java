package org.metadatacenter.readers.geo.metadata;

public class ContributorName
{
  private final String firstName;
  private final String middleInitial;
  private final String lastName;

  public ContributorName(String firstName, String middleInitial, String lastName)
  {
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getMiddleInitial()
  {
    return middleInitial;
  }

  public String getLastName()
  {
    return lastName;
  }

}
