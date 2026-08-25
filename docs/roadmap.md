# Roadmap

> **What goes in this document:** what is pending. One line per item, in execution order — the
> number is the running order, not a topic.
>
> Not here: why something was decided (`docs/decisions.md`), what already happened
> (`docs/log.md`), rule verification status (`docs/domain/rules.md`).
>
> When an item closes, it leaves this file and a dated entry goes into `docs/log.md`, in the
> same change.

## Where the project stands

The Excel engine is complete and reproduces the three real returns to the peso. **M1** and **M3**
are met; **M2 is unverified** and **M5 has not been run**. No code exists, and no stack has been
chosen. P0 is not closed.

## Now

| # | Item | Tag | Blocks |
|---|---|---|---|
| 1 | Decide the privacy line: what of `docs/context/` and `evidence/` may enter git, and what stays local | `[R]` | item 2 |
| 2 | Empty `docs/context/CONTEXT.md` into its destinations and delete it | `[R+A]` | — |
| 3 | Write the fichas for the nine rules sitting at 🟡 (own words + numeric example + test case) — US-P0-000 | `[R]` | closing P0 |
| 4 | Incorporate the contadora's answers; close R9 and the flags on R15–R18 | `[R]` | item 5, filing |
| 5 | File AG2025 — deadline for NIT ending 66 is `[NEEDS CLARIFICATION: 28-sep-2026 was worked out from the business-day table in the research document, never confirmed by the owner, and R10 flags the window's end date as pending against the definitive DIAN 2026 calendar]` | `[R]` | — |
| 6 | Verify M2: confirm the webinar's worked examples are reproduced | `[R]` | closing P0 |
| 7 | Run the M5 willingness-to-pay probe with ≥3 people — US-P0-009 | `[R]` | the P2 go/no-go |

## Next, once P0 closes

| # | Item | Tag |
|---|---|---|
| 8 | Choose the stack. TaxL is front-heavy and near self-service, unlike Tabris — do not inherit its choices | `[R+A]` |
| 9 | Write `CONTRIBUTING.md` once the stack exists | `[A]` |
| 10 | Decide whether a board is needed on top of `/method`, or whether this file is the board | `[R]` |
| 11 | Decide the order of the three specs: `003-calc-engine`, `002-exogena-parser`, `001-free-tier-flow` | `[R]` |
| 12 | Research the legal boundary between "estimation tool" and "tax advisory" — gates tier Y and the tier X disclaimer | `[R]` |

## Cheap and parallel, no blocker

| # | Item | Tag |
|---|---|---|
| 13 | Collect real exógena↔return pairs beyond the owner's, framed as "experiment, no commitment" — they are the only test material for R11–R14 | `[R]` |

## Open questions carried from the plan

| # | Question | Tag |
|---|---|---|
| 14 | Define the exact "key lines" that the $0-difference criterion (M1) applies to | `[R]` |
| 15 | Define a measurable North Star Metric for when real users exist | `[R]` |
| 16 | Set the price anchor in COP for tier X, before the M5 probe asks about a concrete number | `[R]` |

## Later epics

Epic-level, to be broken into slices when reached. Carried from `PLAN.md` §8.

- **US-P1-001** — Accountant review protocol for raising observations on the engine.
- **US-P1-002** — Self-employed: compare the 3 scenarios (real costs / 25% exempt / presumptive).
- **US-P2-001** — Login tied to userId; resume later.
- **US-P2-002** — Menu routing: DIAN validator or simulator.
- **US-P2-003** — Clear disclaimer (draft, not advice).
- **US-P2-004** — Upload exógena file (with curated download guide) → parse, depurate (R11–R14), extract the 4 thresholds and breakdown → free-tier conservative estimate + deadline + applicable rules.
- **US-P2-005** — Tier X purchase flow: full liquidation + deduction validation + normative breakdown + documents checklist.
- **US-P3-001** — Upload certificates one at a time with fail-fast validation (OCR).
- **US-P4-001** — Field-level tooltips and AI alerts; tier Y recommendations, gated on item 12.
