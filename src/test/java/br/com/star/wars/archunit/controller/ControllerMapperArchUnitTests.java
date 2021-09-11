package br.com.star.wars.archunit.controller;

import br.com.star.wars.archunit.required.ArchTest;
import br.com.star.wars.archunit.required.ArchUtils;
import com.tngtech.archunit.core.domain.JavaClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ControllerMapperArchUnitTests implements ArchTest {

    private static final String PACKAGE_NAME = "controller";

    private static final String SUB_PACKAGE_NAME = "mapper";

    private static final String CLASSES_SULFIX_ALLOWED = "ControllerMapper";

    @Test
    @DisplayName("O pacote controller.mapper deve existir")
    public void shouldExistThePackage() {
        Assertions.assertTrue(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME).containsPackage(SUB_PACKAGE_NAME));
    }

    @Test
    @DisplayName("O pacote controller.mapper não deve ter subpacotes")
    public void shouldNotExistSubpackagesInPackage() {
        Assertions.assertTrue(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME).getPackage(SUB_PACKAGE_NAME).getSubpackages().isEmpty());
    }

    @Test
    @DisplayName("O pacote controller.mapper deve ter somente classes com finais permitidos")
    public void shouldOnlyHaveClassesWithAllowedSulfix() {
        final Optional<JavaClass> javaClassOptional = ArchUtils
                .getForbiddenClasses(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME)
                        .getPackage(SUB_PACKAGE_NAME), CLASSES_SULFIX_ALLOWED);
        javaClassOptional.ifPresent(javaClass -> Assertions.fail("Classe " + javaClass.getSimpleName() + " não permitida"));
    }

    @Test
    @DisplayName("As camadas de isolamento devem ser respeitadas")
    public void shouldApplyLayerRestrictions() {
        layeredArchitecture()
                .layer("mapper").definedBy("..controller.mapper")
                .layer("controller").definedBy("..controller")

                .whereLayer("mapper").mayOnlyBeAccessedByLayers("controller")
                .check(ArchUtils.getMainClasses());
    }

}
