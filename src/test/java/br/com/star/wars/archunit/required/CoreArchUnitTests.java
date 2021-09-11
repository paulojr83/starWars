package br.com.star.wars.archunit.required;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class CoreArchUnitTests implements ArchTest {

    @Test
    @DisplayName("Toda boundary deve ser uma interface")
    public void shouldAllBoundaryIsAnInterface() {
        classes()
                .that()
                .haveNameMatching(".*Boundary")
                .should()
                .beInterfaces()
                .check(ArchUtils.getMainClasses());
    }

    @Test
    @DisplayName("Toda boundary deve ter uma implementação de Interactor")
    public void shouldAllInteractorIsAClass() {
        classes()
                .that()
                .haveNameMatching(".*Interactor")
                .should()
                .notBeInterfaces()
                .check(ArchUtils.getMainClasses());
    }

    @Test
    @DisplayName("As camadas de isolamento devem ser respeitadas")
    public void shouldApplyLayerRestrictions() {
        layeredArchitecture()
                .layer("entity").definedBy("..entity")
                .layer("usecase").definedBy("..usecase")
                .optionalLayer("config").definedBy("..config")
                .optionalLayer("property").definedBy("..property")

                .whereLayer("entity").mayOnlyBeAccessedByLayers("usecase")
                .whereLayer("property").mayOnlyBeAccessedByLayers("usecase", "config")
                .check(ArchUtils.getMainClasses());
    }

}
