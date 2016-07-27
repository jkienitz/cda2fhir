package tr.com.srdc.cda2fhir;

import org.openhealthtools.mdht.uml.hl7.vocab.EntityNameUse;
import org.openhealthtools.mdht.uml.hl7.vocab.PostalAddressUse;
import org.openhealthtools.mdht.uml.hl7.vocab.TelecommunicationAddressUse;

import ca.uhn.fhir.model.dstu2.valueset.AddressTypeEnum;
import ca.uhn.fhir.model.dstu2.valueset.AddressUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;

public interface ValueSetsTransformer {
	NameUseEnum EntityNameUse2NameUseEnum(EntityNameUse entityNameUse);
	AddressUseEnum PostalAdressUse2AddressUseEnum(PostalAddressUse postalAddressUse);
	ContactPointUseEnum TelecommunicationAddressUse2ContacPointUseEnum( TelecommunicationAddressUse telecommunicationAddressUse );
	AddressTypeEnum PostalAddressUse2AddressTypeEnum( PostalAddressUse postalAddressUse );
}