<!--
Copyright (C) 2016 SRDC Yazilim Arastirma ve Gelistirme ve Danismanlik Tic. A.S.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->

cda2fhir [![License Info](http://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)](https://github.com/srdc/cda2fhir/blob/master/LICENSE.txt)
===

cda2fhir is a Java library to transform HL7 CDA R2 instances to HL7 FHIR resources. More specifically, cda2fhir enables automatic transformation of
Consolidated CDA (C-CDA) Release 2.1 compliant document instances to the corresponding FHIR DSTU2 resources, wherever possible implementing the
[U.S. Data Access Framework (DAF) FHIR Implementation Guide](http://hl7.org/fhir/daf/daf.html). For this purpose, cda2fhir provides extensible
document transformers, resource transformers, data type transformers and value set transformers. The current implementation provides a
document transformer for Continuity of Care Document (CCD), but further document transformers, e.g. for Discharge Summary or Referral Note
can be easily introduced by reusing the already existing section and entry transformers. Although the cda2fhir library expects C-CDA R2.1 compliant
documents/entries, it has been tested with several older document instances compliant with earlier releases of C-CDA. The official
[HL7 FHIR Validator](https://www.hl7.org/fhir/validation.html#jar) is also integrated for automated validation of the generated FHIR resources.

All the mappings implemented between CDA artifacts and FHIR resources, data types and value sets are documented in this sheet:
https://docs.google.com/spreadsheets/d/15Kv6PFyPh91sH1JMYwLH7D2yjh4HOTy5pjETjQNRyaU/edit?usp=sharing

[Model Driven Health Tools (MDHT)](https://www.projects.openhealthtools.org/sf/projects/mdht/) is used for CDA manipulation and
[HAPI](http://hapifhir.io/) is used for FHIR manipulation. The current implementation produces DSTU2 resources.
We are planning to cover STU3 resources as well, after the specification becomes official.

## Installation

Apache Maven is required to build cda2fhir. Please visit http://maven.apache.org/ in order to install Maven on your system.

Under the root directory of the cda2fhir project run the following:

	$ cda2fhir> mvn install

In order to make a clean install run the following:

	$ cda2fhir> mvn clean install

These will build the cda2fhir library and also run a number of test cases, which will transform some C-CDA Continuity of Care Document (CCD) instances,
and some manually crafted CDA artifacts (e.g. entry class instances) and datatype instances to corresponding FHIR resources, wherever possible using the DAF profile.

## Transforming a CDA document to a Bundle of corresponding FHIR resources

```java
FileInputStream fis = new FileInputStream("src/test/resources/C-CDA_R2-1_CCD.xml");

ClinicalDocument cda = CDAUtil.load(fis);
ICDATransformer ccdTransformer = new CCDTransformerImpl(IdGeneratorEnum.COUNTER);
Config.setGenerateDafProfileMetadata(true);
Config.setGenerateNarrative(true);
Bundle bundle = ccdTransformer.transformDocument(cda);
if(bundle != null)
    FHIRUtil.printJSON(bundle, "src/test/resources/output/C-CDA_R2-1_CCD-w-daf.json");
```

## Transforming a CDA artifact (e.g. an entry class) to the corresponding FHIR resource(s)