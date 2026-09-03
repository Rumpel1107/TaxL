# Roadmap

> **What goes in this document:** what is pending. One line per item, in execution order — the
> number is the running order, not a topic.
>
> Not here: why something was decided (`docs/decisions.md`), what already happened
> (`docs/log.md`), rule verification status (`docs/domain/rules.md`). Personal tax administration
> is not tracked here (D32).
>
> When an item closes, it leaves this file and a dated entry goes into `docs/log.md`, in the
> same change.

## Where the project stands

The Excel engine is complete and reproduces the three reference returns to the peso. **M1** and
**M3** are met; **M2 is unverified** and **M5 has not been run**. No code exists, and no stack has
been chosen. P0 is not closed. The first slice is specified in `docs/specs/manual-liquidation/`.

## Now

| # | Item | Tag | Blocks |
|---|---|---|---|
| 1 | Resolve or accept in writing the nine open flags on the rule sheets (R3–R6, R8, R9, R10, R15, R16) — US-P0-000 | `[R]` | closing P0 |
| 2 | Choose the stack. TaxL is front-heavy and near self-service, unlike Tabris — do not inherit its choices. Includes how a running UI is inspected and evidenced (Chrome DevTools MCP or equivalent) | `[R+A]` | the first slice |
| 3 | Build the first vertical slice, specified in `docs/specs/manual-liquidation/` — guest only, no exógena, no accounts, no fiscal figure stored, with the three reference returns as golden tests | `[R+A]` | everything testable |
| 4 | Verify M2: confirm the webinar's worked examples are reproduced | `[R]` | closing P0 |
| 5 | Run the M5 willingness-to-pay probe with ≥3 people — US-P0-009 | `[R]` | the P2 go/no-go |
| 6 | Run the manual PoC: liquidate 5 real employee cases with the Excel, free, participants' documents deleted on delivery — the deliverable is the list of scenarios the engine does not cover | `[R]` | the beta scope |

## Next, once P0 closes

| # | Item | Tag |
|---|---|---|
| 7 | Fill in the Dev setup section of `CONTRIBUTING.md` once the stack exists | `[A]` |
| 8 | Decide whether a board is needed on top of `/method`, or whether this file is the board | `[R]` |
| 9 | Research the legal boundary between "estimation tool" and "tax advisory" — gates tier Y and the disclaimer of AC11 | `[R]` |
| 10 | Delete `docs/context/` once the app reproduces the reference returns without it — D33 | `[R]` |

## Cheap and parallel, no blocker

| # | Item | Tag |
|---|---|---|
| 11 | Collect real exógena↔return pairs, framed as "experiment, no commitment" — they are the only test material for R11–R14, and they never enter the repository (D31) | `[R]` |

## Open questions carried from the plan

| # | Question | Tag |
|---|---|---|
| 12 | Define a measurable North Star Metric for when real users exist | `[R]` |
| 13 | Set the price anchor in COP for tier X, before the M5 probe asks about a concrete number | `[R]` |

## Later epics

Epic-level, to be broken into slices when reached. Carried from `PLAN.md` §8.

- **US-P1-001** — Accountant review protocol for raising observations on the engine.
- **US-P1-002** — Self-employed: compare the 3 scenarios (real costs / 25% exempt / presumptive).
- **US-P2-001** — Login tied to userId; resume later.
- **US-P2-002** — Menu routing: DIAN validator or simulator.
- **US-P2-004** — Upload exógena file (with curated download guide) → parse, depurate (R11–R14), extract the 4 thresholds and breakdown → free-tier conservative estimate + deadline + applicable rules.
- **US-P2-005** — Tier X purchase flow: full liquidation + deduction validation + normative breakdown + documents checklist.
- **US-P3-001** — Upload certificates one at a time with fail-fast validation (OCR).
- **US-P4-001** — Field-level tooltips and AI alerts; tier Y recommendations, gated on the legal-boundary item.
