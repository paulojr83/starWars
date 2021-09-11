package br.com.star.wars.archunit.required;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ArchUtils {
    private static final String PROJECT_BASE = "br.com.star.wars";

    public static Optional<JavaPackage> getForbiddenSubPackage(final JavaPackage javaPackage, final String allowedPackages) {
        final List<String> allowedPackageList = Arrays.asList(allowedPackages.split(","));

        final List<JavaPackage> packageForbiddens =
                javaPackage.getSubpackages()
                        .stream()
                        .filter(subPackage -> packageNotAllowed(subPackage.getRelativeName(), allowedPackageList))
                        .limit(1)
                        .collect(Collectors.toList());

        return packageForbiddens.stream().findFirst();
    }

    public static JavaClasses getMainClasses() {
        return new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importClasspath();

    }

    public static JavaPackage getMainPackage() {
        return getMainClasses().getDefaultPackage().getPackage(PROJECT_BASE);
    }

    public static JavaPackage getInfrastructurePackage() {
        return getMainPackage().getPackage("infrastructure");
    }

    public static JavaPackage getInfrastructureSubPackage(final String name) {
        return getInfrastructurePackage().getPackage(name);
    }

    public static boolean containsInfrastructureSubPackage(final String name) {
        return getInfrastructurePackage().containsPackage(name);
    }

    public static boolean packageNotAllowed(final String name, final List<String> packagesAllowed) {
        return !packagesAllowed.contains(name);
    }

    public static boolean classSulfixNotAllowed(final String name, final String suffixs) {
        final List<String> suffixList = Arrays.asList(suffixs.split(",").clone());
        return suffixList.stream().noneMatch(name::endsWith);
    }

    public static Optional<JavaClass> getForbiddenClasses(final JavaPackage mainPackage, final String classesSulfixAllowed) {
        final Set<JavaClass> classes = mainPackage.getClasses();

        final List<JavaClass> classesForbidden  = classes
                .stream()
                .filter(javaClass -> classSulfixNotAllowed(javaClass.getSimpleName(), classesSulfixAllowed))
                .limit(1)
                .collect(Collectors.toList());

        return classesForbidden.stream().findFirst();
    }
}
