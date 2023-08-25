package org.hahunavth.hibernate.strategies;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Converts camel case names to snake case names.
 * <p>
 *     For example:
 *          <br>
 *          Customer -> customers
 *          <br>
 *          firstName -> first_name
 *          <br>
 *          lastName -> last_name
 *          <br>
 *          emailAddress -> email
 *     <br>
 *     Using:
 *          <br>
 *          <code>
 *          configuration.setPhysicalNamingStrategy(new SQLPhysicalNamingStrategy());
 *          <code/>
 */
public class SQLPhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToSnakeCase(identifier);
    }

    private Identifier convertToSnakeCase(final Identifier identifier) {
        if (identifier == null) {
            return null;
        }

        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText()
            .replaceAll(regex, replacement)
            .toLowerCase();

        if (newName.equals(identifier.getText())) {
            return identifier;
        }
        return Identifier.toIdentifier(newName);
    }
}
