# Contributing to TaxL

How this project is run — for any collaborator, human or agent. **This file is the single
source of truth for how the project is run.** For what the product is and is not, see
`PLAN.md`. Process — phases and gates — lives in the `/method` skill and is never redefined
here.

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

**When an item closes,** remove it from `docs/roadmap.md` and write the dated entry in
`docs/log.md` in the same change.

## Owner tags

Every work item carries one. They mark who owns the outcome, not who types.

- `[R]` — **Domain.** Executed personally by the owner: tax rules, normative validation, business decisions. Agents may research and propose, never decide.
- `[A]` — **Scaffolding.** Delegable to agents: UI, boilerplate, structure, deploy.
- `[R+A]` — **Collaborative.** Agent proposes, owner validates and owns the result.

## Code conventions

- **English** for code, names, comments and project documents.
- **English is the default product language; Spanish is a supported option** selected per user. All user-facing text MUST go through strings — never hardcode user-facing text in any language.
- **In project documents, Spanish survives only in three places:** acronyms (UVT, DIAN, INCRNGO, E.T.), normative citations (`art. 336 E.T.`, `Ley 2277 de 2022`, `DIAN Res. 000120 de 2024`), and `exógena`, which has no equivalent. The glossary may carry the Spanish original in parentheses. Every other domain term is English — general schedule, tax year, exempt income, severance pay, advance payment. Figures keep Colombian format (`1.340`, `$56.832.000`).

## Confidential material

`docs/context/` is local and gitignored: the reference case and the source documents the
engine is built against. Read it freely, never copy from it into a committed file — not even
rewritten or anonymised (constitution principle 11).

## Dev setup

There is no stack yet — choosing it is an open item in `docs/roadmap.md`. Nothing in this
repository is runnable today. How to install, run and test goes in this section once the
stack is decided.
