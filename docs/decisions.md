# Decisions

> **What goes in this document:** why something already settled was settled, with the alternative
> that was rejected. Append-only.
>
> Not here: what is still pending (`docs/roadmap.md`), what happened (`docs/log.md`), tax rules
> and their sources (`docs/domain/rules.md`).

**A row is never edited or deleted once accepted.** Changing your mind adds a new row and flips
the old row's status. Ids are stable and never reused.

| Status | Meaning |
|---|---|
| `accepted` | In force |
| `superseded by Dnn` | Replaced by a later decision |
| `deprecated` | Stopped applying, with nothing replacing it |

Rows carried over from `PLAN.md` §13 and from the discovery sessions recorded in
`docs/context/CONTEXT.md` §5. Where the original record did not preserve the rejected
alternative, the cell says **not recorded** rather than a reconstruction.

## Product and scope

| # | Date | Decision | Rejected alternative | Why | Status |
|---|---|---|---|---|---|
| D01 | — | North Star = "Understand and verify before filing" | not recorded | The DIAN already fills the form; the gap is understanding it | `accepted` |
| D02 | — | MVP profile = employee (asalariado) | Covering several taxpayer profiles at once | Cut by profile to keep the riskiest logic small | `accepted` |
| D03 | — | Validation suite = 3 personal past returns + webinar examples | Synthetic test cases | Free ground truth that is already known to be correct | `accepted` |
| D04 | 2026-07-06 | Monetization funnel: free ceiling estimate → X (full liquidation) → Y (next-year recommendations) | Single paid product | The free estimate is deliberately the ceiling, so X sells "see how much you can lower it" | `accepted` |
| D05 | 2026-07-06 | Product entry point = exógena file upload | Manual data entry as the entry point | Nobody enters their data with discipline; manual entry stays as fallback | `accepted` |
| D06 | 2026-07-06 | Exógena parsing moved P3 → P2 | Leaving it in P3 | It sits on the free tier's critical path; accepts advancing the R11–R14 technical risk | `accepted` |
| D07 | 2026-07-06 | Form 210 draft output stays post-P2, announced in X as "coming soon"; tier Y gated on legal-boundary research | Shipping the draft output inside X | High value but high consequence on error | `accepted` |
| D08 | 2026-07-06 | No committed deadline; Tabris and P0 advance in parallel | Fixing a date for the 2026 season | Entering mid-season 2026 is acceptable for early validation | `accepted` |
| D09 | 2026-08-10 | C2 — MVP automates the owner's exact case plus out-of-scope detection | Speculative coverage of other profiles | Coverage expands case by case as real scenarios appear | `accepted` |
| D10 | 2026-08-10 | C3 — Parameters editable by non-technical users (accountants) as a first-class feature | Parameters as config files | An accountant must be able to update a legal value without a developer | `accepted` |

## How the product treats data

| # | Date | Decision | Rejected alternative | Why | Status |
|---|---|---|---|---|---|
| D11 | 2026-08-10 | A1 — Exógena is the starting point only; official certificates win on conflict | Trusting exógena as authoritative | Two entities can report the same money; a fiduciaria's disbursement reads as income | `accepted` |
| D12 | 2026-08-10 | A2 — Detect income *missing* from exógena via certificates | Treating exógena as complete | Found live: a Global66 income absent from the file | `accepted` |
| D13 | 2026-08-10 | A3 — Field→calculation mapping is proprietary knowledge, not a label read-off | Mapping by the file's own labels | Labels are ambiguous: "susceptible de beneficio" is the FE field, "tras ajustes por notas" feeds the compras threshold | `accepted` |
| D14 | 2026-08-10 | A4/A5 — On any discrepancy or unrecognized item, show the diff and let the user decide | Silently picking a side | The product sells trust; a silent choice is the one thing that destroys it | `accepted` |
| D15 | 2026-08-10 | B1 — Flow starts with exógena upload, which then produces the suggested-documents checklist | Asking for certificates up front | The file is what the user can get alone, in one place | `accepted` |
| D16 | 2026-08-10 | B4 — Show everything, including items with zero tax impact | Showing only what changes the result | Completeness builds trust; messaging distinguishes an error with impact from a form error | `accepted` |
| D17 | 2026-08-10 | B5 — Apply the law as written as the baseline; a professional or the user may override with criterio | Encoding a professional's criterio as the default | The tool prepares for the accountant, it does not replace them | `accepted` |

## Product positioning

| # | Date | Decision | Rejected alternative | Why | Status |
|---|---|---|---|---|---|
| D18 | 2026-08-10 | B2 — The in-cap / out-cap (40%) bucket architecture is made visible as the core pedagogical differentiator | Hiding the mechanics behind a final figure | It is the part no competitor explains | `accepted` |
| D19 | 2026-08-10 | B3 — "Exceso perdido" (benefits lost to the cap) is the candidate headline insight | Leading with the tax figure | Across three real years the lost benefit ran into tens of millions of pesos | `accepted` |
| D20 | 2026-08-10 | B6 — "Validate a past return" is a hypothesis for the professional segment | Treating contadores as out of scope | To be tested in the M5 probe, not assumed | `accepted` |

## Method and repository

| # | Date | Decision | Rejected alternative | Why | Status |
|---|---|---|---|---|---|
| D21 | — | Backlog and plan live as version-controlled markdown in the repo | A tracker as the source of truth | Single source of truth, readable by agents | `accepted` |
| D22 | — | Board tool = GitHub (Issues + Projects) | not recorded | Never implemented, and `/method` plus `docs/roadmap.md` now cover what it was for. Whether a board is still wanted is roadmap item 10 | `deprecated` |
| D23 | 2026-07-06 | Owner tags `[R]` / `[A]` / `[R+A]` adopted | Treating every task as equally delegable | Marks who owns the outcome, not who types | `accepted` |
| D24 | 2026-07-06 | Parameter values written into acceptance criteria require a verification ficha before being trusted | Trusting webinar-sourced values because they were already written down | Written ≠ verified | `accepted` |
| D25 | 2026-08-10 | D2 — Ground truth is reconstructed from original source documents, never copied from prior results | Reusing the previous year's computed figures | This is how every AG2023 finding surfaced | `accepted` |
| D26 | 2026-08-10 | D3 — Golden tests run automatically on every change | Human review as the regression check | Two caps broke silently when the 2025 file was rebuilt by copy | `accepted` |
| D27 | 2026-08-24 | `/method` is the single process; the methodology sections in `PLAN.md` and `memory/constitution.md` are removed | Keeping the Kanban + DoR/DoD alongside `/method` | Three overlapping processes meant none was followed | `accepted` |
| D28 | 2026-08-24 | Documentation split into `PLAN.md` (framing), `docs/roadmap.md`, `docs/decisions.md`, `docs/log.md`, `docs/domain/` | Keeping one `PLAN.md` | Append-only content was burying the volatile content; `PLAN.md` §11 went stale unnoticed | `accepted` |
| D29 | 2026-08-24 | The agent applies changes to files; the owner reviews | "Propose, don't apply" | Applying edits by hand in this environment is friction with no benefit | `accepted` |
| D30 | 2026-08-24 | Decisions carry an id and a status, and are never edited once accepted | Editing or deleting a row when it stops applying | Editing destroys the record that the earlier reasoning existed; D22 needed this the day it was written | `accepted` |
