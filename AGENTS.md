# AGENTS.md

Entry point for any AI agent (or human) working on this repository. This file holds no rules
of its own — it names where they live, so there is nothing here to drift out of sync.

- **How this project is run** — where each document lives, owner tags, conventions, setup: @CONTRIBUTING.md
- **What is pending:** [`docs/roadmap.md`](docs/roadmap.md). Read it first when picking up work.
- **What the product is and is not:** [`PLAN.md`](PLAN.md). The principles it does not negotiate: @memory/constitution.md
- **Process** — phases and gates: the `/method` skill. **Collaboration style** — how the maintainer works with an agent: that same skill's `collaboration.md`. Both are personal and live outside this repository.

The two files written with `@` are imported, not linked: an agent tool that reads this file loads
them in full, so their rules are in context from the first message instead of whenever someone
remembers to open them. Keep them as imports. *Why: in another project of this workspace a session
writing documents never opened its `CONTRIBUTING.md` — the rule against personal data in a public
repository was in force and unread, and personal data was committed.*
