# SE-EDU Java Coding Standard: Basic and Intermediate Rules

Source: [SE-EDU Java coding standard (basic + intermediate)](https://se-education.org/guides/conventions/java/intermediate.html)

Apply all relevant rules below. For uncovered topics, follow the Google Java Style Guide.

## Naming

- Use lowercase package names. Begin school-project package hierarchies with the group or project name.
- Name classes and enums with nouns in PascalCase.
- Name variables in camelCase and methods with verbs in camelCase.
- Name constants in SCREAMING_SNAKE_CASE and give related constants a common prefix.
- Test methods may use `featureUnderTest_testScenario_expectedBehavior` with unneeded parts omitted.
- Treat abbreviations and acronyms as ordinary words within identifiers, such as `exportHtmlSource`.
- Write names in English.
- Use descriptive names for larger scopes. Reserve short scratch names such as `i` for small scopes.
- Reserve `j`, `k`, and later iterator names for nested loops.
- Name booleans with prefixes such as `is`, `has`, `was`, `can`, or `should`.
- Use plural names for collections and arrays of objects.

## Layout and whitespace

- Indent with four spaces, never tabs.
- Prefer lines shorter than 110 characters and never exceed 120 characters.
- Indent continuations eight spaces beyond the parent line.
- Break after commas and before operators. Keep method names attached to their opening parenthesis.
- Prefer higher-level line breaks and use the documented ternary layouts.
- Use K&R braces and the standard block forms for methods and control statements.
- Add `// Fallthrough` when a traditional switch case intentionally has no `break`.
- Surround operators with spaces and add spaces after keywords, commas, and for-loop semicolons.
- Separate logical units within a block with one blank line and leave no trailing whitespace.

## Packages, imports, types, and variables

- Put every class in a package.
- Keep import ordering consistent; use explicit, minimal imports and never wildcard imports.
- Attach array brackets to the type, for example `int[] values`.
- Initialize variables at declaration and declare them in the smallest practical scope.
- If no valid initial value exists, leave the variable uninitialized rather than using a fake value.
- Do not expose mutable class variables as public except in behavior-free data classes.

## Control statements

- Always wrap loop bodies in braces.
- Put conditional bodies on separate lines and always wrap them in braces.

## Comments and Javadocs

- Write comments in English with American spelling and no local slang.
- Write descriptive Javadocs for every public class and public method.
- Javadocs may be omitted for getters/setters, tests, and overrides whose inherited docs apply unchanged.
- Put `/**` on its own line, align `*`, and begin with a short summary sentence.
- Method summaries use forms such as `Returns`, `Adds`, or `Sends`.
- Separate descriptions from tags with a blank line and punctuate parameter descriptions.
- Include all useful `@param` tags or omit all when the parameter names are self-explanatory.
- Omit `@return` for void methods or when the return value is obvious.
- Place no blank line between Javadocs and declarations; indent comments with their code.

## Review checklist

- Confirm packages, imports, names, layout, braces, variable scope, and public-API Javadocs.
- Compile the affected code and run relevant tests.
