package com.erg01.backbone;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.erg01.backbone");

        noClasses()
            .that()
                .resideInAnyPackage("com.erg01.backbone.service..")
            .or()
                .resideInAnyPackage("com.erg01.backbone.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.erg01.backbone.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
