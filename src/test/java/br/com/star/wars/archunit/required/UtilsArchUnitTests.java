package br.com.star.wars.archunit.required;

import com.tngtech.archunit.core.domain.JavaClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class UtilsArchUnitTests implements ArchTest {
        
    private static final String PACKAGE_NAME = "utils";

        private static final String CLASSES_SULFIX_ALLOWED = "Utils";

        @Test
        @DisplayName("O pacote utils deve existir")
        public void shouldExistThePackage() {
                Assertions.assertTrue(ArchUtils.containsInfrastructureSubPackage(PACKAGE_NAME));
        }

        @Test
        @DisplayName("O pacote utils não deve ter subpacotes")
        public void shouldNotExistSubPackagesInPackage() {
                Assertions.assertTrue(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME).getSubpackages().isEmpty());
        }

        @Test
        @DisplayName("O pacote utils deve ter somente classes com finais permitidos")
        public void shouldOnlyHaveClassesWithAllowedSulfix() {
            final Optional<JavaClass> javaClassOptional = ArchUtils
                                .getForbiddenClasses(ArchUtils.getInfrastructureSubPackage(PACKAGE_NAME), CLASSES_SULFIX_ALLOWED);
                javaClassOptional.ifPresent(javaClass -> Assertions.fail("Classe " + javaClass.getSimpleName() + " não permitida"));
        }

        @Test
        @DisplayName("As camadas de isolamento devem ser respeitadas")
        public void shouldApplyLayerRestrictions() {
                layeredArchitecture()
                                .layer("utils").definedBy("..utils")
                                .layer("infrastructure").definedBy("..infrastructure..")

                                .whereLayer("utils").mayOnlyBeAccessedByLayers("infrastructure")
                                .check(ArchUtils.getMainClasses());
        }

}
