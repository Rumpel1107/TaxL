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
been chosen. P0 is not closed.

## Now

| # | Item | Tag | Blocks |
|---|---|---|---|
| 1 | Empty `docs/context/CONTEXT.md` into its destinations under D31, and delete it | `[R+A]` | — |
| 2 | Write the fichas for the ten rules sitting at 🟡 (own words + illustrative numeric example + test case) — US-P0-000 | `[R]` | closing P0 |
| 3 | Close R9 and the flags on R15–R18, pending a professional's answer | `[R]` | closing P0 |
| 4 | Verify M2: confirm the webinar's worked examples are reproduced | `[R]` | closing P0 |
| 5 | Run the M5 willingness-to-pay probe with ≥3 people — US-P0-009 | `[R]` | the P2 go/no-go |

## Next, once P0 closes

| # | Item | Tag |
|---|---|---|
| 6 | Choose the stack. TaxL is front-heavy and near self-service, unlike Tabris — do not inherit its choices | `[R+A]` |
| 7 | Write `CONTRIBUTING.md` once the stack exists | `[A]` |
| 8 | Decide whether a board is needed on top of `/method`, or whether this file is the board | `[R]` |
| 9 | Decide the order of the three specs: `003-calc-engine`, `002-exogena-parser`, `001-free-tier-flow` | `[R]` |
| 10 | Research the legal boundary between "estimation tool" and "tax advisory" — gates tier Y and the tier X disclaimer | `[R]` |
| 11 | Delete `docs/context/evidence/` once the app reproduces the reference returns without it — D33 | `[R]` |

## Cheap and parallel, no blocker

| # | Item | Tag |
|---|---|---|
| 12 | Collect real exógena↔return pairs, framed as "experiment, no commitment" — they are the only test material for R11–R14, and they never enter the repository (D31) | `[R]` |

## Open questions carried from the plan

| # | Question | Tag |
|---|---|---|
| 13 | Define the exact "key lines" that the $0-difference criterion (M1) applies to | `[R]` |
| 14 | Define a measurable North Star Metric for when real users exist | `[R]` |
| 15 | Set the price anchor in COP for tier X, before the M5 probe asks about a concrete number | `[R]` |

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
- **US-P4-001** — Field-level tooltips and AI alerts; tier Y recommendations, gated on item 10.
