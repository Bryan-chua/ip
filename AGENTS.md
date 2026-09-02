# Project context

This repository is a starter template for a greenfield Java project used in an introductory software engineering course in an undergraduate computer science program. Students use it as the starting point for their own projects.

# Default user context

Unless the user says otherwise, assume that you are assisting a student working on a project in this repository. If the user identifies themselves as an instructor or another project stakeholder, adapt your response to that role.

# Student profile

* Prior knowledge: Basic Java and OOP concepts.
* Level of programming experience: [to be filled]
* IDE and level of expertise: [to be filled]

# Guidance for interacting with users

* Explain the rationale for significant actions: what you did and why.
* Keep explanations brief but instructive, supporting learning through responsible use of AI. For example:

  * When suggesting a Git command, briefly explain what it does.
  * Add explanatory Javadoc comments to all classes and to nontrivial methods and fields when their purpose or behavior is not obvious.
  * Make generated code as self-explanatory as possible, and include explanatory comments where they improve understanding.
  * When faced with a design choice, choose the simplest option that is sufficient for the requirements, while briefly explaining relevant more advanced alternatives.

# Project-specific requirements

## Java version:

Ensure that Java 25 is used when running the application or build tasks. On macOS, use `sdk use java 25.0.3.fx-zulu` to switch to Java 25 if needed.

## Git

Use lightweight tags unless the user requests an annotated tag.
When proposing or creating a commit message, include enough detail to explain the rationale for the change.
Do not commit or push unless explicitly asked.

# Project Agent Instructions

## Java coding standard

For every task that creates, edits, generates, or reviews Java code in this repository:

1. Load and follow `.codex/skills/seedu-java-coding-standard/SKILL.md`.
2. Treat its basic and intermediate SE-EDU rules as mandatory for all Java code in the project.
3. Use the Google Java Style Guide for topics the SE-EDU standard does not cover.
4. Review changed Java files against the skill checklist and compile or test them when practical.

Preserve explicit user requirements when they conflict with a style preference, and report any exception.

## Git standard

For every task that creates, proposes, reviews, or amends a commit or names a branch:

1. Load and follow `.codex/skills/seedu-git-standard/SKILL.md`.
2. Treat the SE-EDU commit-message and branch-naming rules in that skill as mandatory.
3. Inspect the staged changes before committing and ensure the message accurately explains the change.
4. Include a properly wrapped body for every non-trivial commit.

These instructions do not authorize creating commits, branches, pushes, or history rewrites without the user's request.
