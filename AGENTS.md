# AGENTS.md

Guidelines for any AI agent (or human) working on this repository.

## Where things live

| File | What goes in it |
|---|---|
| `PLAN.md` | What the product is, who it is for, and what it deliberately is not |
| `docs/roadmap.md` | What is pending. One line per item, in execution order. **Read this first** |
| `docs/decisions.md` | Why something already settled was settled, with the rejected alternative |
| `docs/log.md` | What happened, dated |
| `docs/domain/` | The tax rules the engine implements, with primary source and verification status |
| `docs/reference/` | Verified normative research, and closed material kept as input for later specs |
| `memory/constitution.md` | The principles this project does not negotiate |
| `CONTRIBUTING.md` | How the project is run and tested — **does not exist yet**, no stack chosen |

Process — phases and gates — lives in the `/method` skill and is never redefined here.

## Working style

- **Language:** talk to the user in Spanish. Write code, names, comments and project documents in English.
- **English is the default product language; Spanish is a supported option** selected per user. All user-facing text MUST go through strings — never hardcode user-facing text in any language.
- **One step at a time.** Present a single step and wait for the user to confirm it before moving to the next. Do not lay out all steps upfront.
- **Explain before doing.** For each step, explain what/how/why in plain language first. Do not re-explain concepts already established.
- **Apply, then report.** Write changes to the files directly; the user works as reviewer, not as typist. Report precisely what changed and where, and say plainly which parts were carried over unchanged and which were rewritten.
- **Let the user set the pace.** Don't end messages pushing to advance.
- **Git is the user's.** The user runs git commands himself. When asked for a commit message, draft it only (subject line + terse one-line bullets, matching the repo's history) — don't run git.
- **When an item closes,** remove it from `docs/roadmap.md` and write the dated entry in `docs/log.md` in the same change.

## Owner tags

Every work item carries one. They mark who owns the outcome, not who types.

- `[R]` — **Domain.** Executed personally by the owner: tax rules, normative validation, business decisions. Agents may research and propose, never decide.
- `[A]` — **Scaffolding.** Delegable to agents: UI, boilerplate, structure, deploy.
- `[R+A]` — **Collaborative.** Agent proposes, owner validates and owns the result.

## Dev setup

There is no stack yet — choosing it is an open item in `docs/roadmap.md`. Nothing in this
repository is runnable today. `CONTRIBUTING.md` gets written when the stack is decided, and
holds how to run and test from then on.
