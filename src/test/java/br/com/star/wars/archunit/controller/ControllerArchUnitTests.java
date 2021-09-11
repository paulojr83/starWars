package br.com.star.wars.archunit.controller;

import br.com.star.wars.archunit.required.ArchTest;
import br.com.star.wars.archunit.required.ArchUtils;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ControllerArchUnitTests implements ArchTest {

    private static final String PACKAGE_NAME = "controller";

    private static final String PACKAGES_ALLOWED = "mapper,request,response,handler";

    private static final String CLASSES_SULFIX_ALLOWED = "Controller";

    @Test
    @DisplayName("O pacote controller deve existir")
    public void shouldExistThePackage() {
        Assertions.assertTrue(ArchUtils.containsInfrastructureSubPackage(PACKAGE_NAME));
    }

    @Test
    @DisplayName("O pacote controller deve ter somente pacotes permitidos")
    public void shouldOnlyHaveAllowedPackagesInPackage() {
        final Optional<JavaPackage> packageOptional = ArchUtils
                .getForbiddenSubPackage(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME), PACKAGES_ALLOWED);
        packageOptional.ifPresent(javaPackage -> Assertions.fail("Pacote " + javaPackage.getName() + " não permitido"));
    }

    @Test
    @DisplayName("O pacote controller deve ter somente classes com finais permitidos")
    public void shouldOnlyHaveClassesWithAllowedSulfix() {
        final Optional<JavaClass> javaClassOptional = ArchUtils
                .getForbiddenClasses(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME), CLASSES_SULFIX_ALLOWED);
        javaClassOptional.ifPresent(javaClass -> Assertions.fail("Classe " + javaClass.getSimpleName() + " não permitida"));
    }

    @Test
    @DisplayName("As camadas de isolamento devem ser respeitadas")
    public void shouldApplyLayerRestrictions() {
        layeredArchitecture()
                .layer("controller").definedBy("..controller")

                .whereLayer("controller").mayNotBeAccessedByAnyLayer()
                .check(ArchUtils.getMainClasses());
    }
}
