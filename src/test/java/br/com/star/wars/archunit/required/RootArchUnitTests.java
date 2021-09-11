package br.com.star.wars.archunit.required;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class RootArchUnitTests implements ArchTest {

    static final String ROOT_PACKAGES_ALLOWED = "core,infrastructure";

    static final String CLASSES_SULFIX_ALLOWED = "Application";

    @Test
    @DisplayName("Na base do projeto deve ter somente pacotes permitidos")
    public void shouldOnlyHaveCoreAndInfrastructureMainPackage() {
        final Optional<JavaPackage> packageOptional = ArchUtils
                .getForbiddenSubPackage(ArchUtils.getMainPackage(), ROOT_PACKAGES_ALLOWED);
        packageOptional.ifPresent(javaPackage -> Assertions.fail("Pacote " + javaPackage.getName() + " não permitido"));
    }

    @Test
    @DisplayName("Na base do projeto deve ter somente classes com finais permitidos")
    public void shouldOnlyHaveClassesWithAllowedSulfix() {
        final Optional<JavaClass> javaClassOptional = ArchUtils.getForbiddenClasses(ArchUtils.getMainPackage(), CLASSES_SULFIX_ALLOWED);
        javaClassOptional.ifPresent(javaClass -> Assertions.fail("Classe " + javaClass.getSimpleName() + " não permitida"));
    }
}
