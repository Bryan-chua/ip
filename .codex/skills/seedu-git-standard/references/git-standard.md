# SE-EDU Git Conventions

Source: [SE-EDU Git conventions](https://se-education.org/guides/conventions/git.html)

## Commit subject

- Give every commit a clear subject.
- Aim for 50 characters; never exceed 72.
- Use imperative mood, as in `Add task validation`.
- Capitalize the first letter and do not end with a period.
- An optional `<scope>:` or `<category>:` prefix may be used when useful.

## Commit body

- Add a body for every non-trivial commit.
- Separate it from the subject with one blank line.
- Wrap body text at 72 characters and separate paragraphs with blank lines.
- Use bullets when they communicate the change more clearly.
- Explain what changed and why; let the diff show how.
- Describe the existing situation in present tense and the action in imperative mood.
- Avoid redundant words such as `currently` and information already captured in code comments.
- If the explanation becomes excessive, consider splitting the commit into smaller cohesive commits.

A useful order is: situation, reason for change, action taken, rationale for that approach, and other relevant context.

## Branch names

- Use meaningful keywords in kebab-case, such as `refactor-ui-tests`.
- For issue-related work, use `issueNumber-keywords`, such as `1234-ui-freeze-error`.
