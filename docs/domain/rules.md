# Domain rules — registry

> **What goes in this document:** every tax rule the engine implements, its primary source, and
> how far its verification has got. Nothing else — the *why* of a product decision goes in
> `docs/decisions.md`, what is pending goes in `docs/roadmap.md`.
>
> Owner: `[R]`. Agents may research and propose; they never close a rule.

## What "closed" means

A rule is closed when its **rule sheet** exists: values, source, formula, the conditions the engine
must respect, and a test case in the validation suite. Reading the source is not closing it.
Rule sheet examples are invented figures, never values taken from a real return (D31).

| Status | Meaning |
|---|---|
| ✅ | Rule sheet written: values + source + formula + conditions + test case |
| 🟡 | Primary source located and verified, rule sheet not yet written |
| ⬜ | Open — source not verified, or blocked |

The rules at 🟡 have their primary source closed; the writing step is what is missing. Rule sheets
live in `docs/domain/rule-sheets/`.

## Calculation rules

| # | Rule | Primary source | Status |
|---|---|---|---|
| R1 | UVT value per tax year | DIAN Res. 001264/2022 (2023), 000187/2023 (2024), 000193/2024 (2025) | 🟡 |
| R2 | Filing-obligation thresholds (4, in UVT) | E.T. arts. 592, 593, 594-3; DUT 1625/2016 art. 1.6.1.13.2.7 | 🟡 |
| R3 | INCRNGO — mandatory health & pension contributions | E.T. arts. 55, 56; Ley 100 de 1993 arts. 20, 25–27 | 🟡 |
| R4 | 25% exempt labor income, cap 790 UVT | E.T. art. 206 num. 10 (Ley 2277/2022 art. 2) | 🟡 |
| R5 | Dependents, both modalities + caps | E.T. art. 387 inc. 2 and art. 336 num. 3 inc. 2; Decreto 2231/2023 | 🟡 |
| R6 | Individual caps: prepaid health, mortgage interest, AFC/voluntary pension, GMF | E.T. arts. 387, 119, 126-1, 126-4, 115; DIAN Concepto 3591/2025 | 🟡 |
| R7 | Global 40% / 1.340 UVT cap; capped vs uncapped items | E.T. art. 336 num. 3 | ✅ |
| R8 | Art. 241 progressive table + fiscal adjustment | E.T. art. 241 (Ley 2010/2019 art. 34); art. 70; Decretos 128/2024, 174/2025, 0449/2026 | 🟡 |
| R9 | Withholdings; advance payment; balance due vs refund | E.T. arts. 383, 807; Form 210 | ⬜ |
| R10 | Filing calendar by ID digits | Decreto 2229/2023 (permanent calendar) | 🟡 |

## Rules the engine already implements, added after the registry was first written

Surfaced during the Excel work and carried here on 2026-08-24 so the registry matches what the
engine actually does.

| # | Rule | Primary source | Status |
|---|---|---|---|
| R15 | Exempt severance pay, gradual table | E.T. art. 206 num. 4 | 🟡 |
| R16 | Meal allowance payments are not income under both monthly UVT conditions | E.T. art. 387-1 | 🟡 |
| R17 | AFC contingent withholding mechanics | E.T. art. 126-4; DUT 1625 de 2016 art. 1.2.4.1.32; DIAN Concepto 4 de 2002, Oficio 33234 de 2018 | ⬜ |
| R18 | Fondo de Solidaridad Pensional as INCRNGO | E.T. art. 55; Ley 100 de 1993 arts. 20, 25–27; DIAN Oficio 909171 de 2021 | ⬜ |
| R19 | Sale of primary residence, exemption | E.T. art. 311-1 — out of MVP scope | ⬜ |

## Exógena depuration rules

The #1 technical unknown. Nothing has been verified; the test material is the three real
exógena↔return pairs held locally.

| # | Rule | Note | Status |
|---|---|---|---|
| R11 | Structure/format of the citizen-downloadable exógena file (sheets, concepts, versions) | Discovered with real files | ⬜ |
| R12 | Mapping: exógena concept → income type / schedule | Core of the parser | ⬜ |
| R13 | Exclusions & false income: loan disbursements, refunds, transfers between own accounts, gross third-party amounts that aren't taxable income | If this fails, the free estimate scares users with a WRONG number and destroys the trust the product sells | ⬜ |
| R14 | What can be confidently inferred from exógena and what cannot | Defines the boundary of the free estimate; when ambiguous, show a range, not a single figure | ⬜ |

## Open flags on otherwise verified rules

These are why a 🟡 is not a ✅. Each must be resolved or accepted in writing before its rule sheet closes.

- **R9 — one figure still unexplained.** The method is no longer open: from the third return
  onwards the filer chooses between the 75% of the current year's tax and the two-year average,
  and both are lawful. Neither reproduces the amount actually settled in the reference case, and
  which base was used is unknown.
- **R8 — one decree to confirm.** The TY2025 fiscal adjustment of 5,81% comes from Decreto 0449 de
  2026, issued close to the research date; number and date need checking against the Diario Oficial.
  It affects fixed assets / occasional gains, not the ordinary employee depuration.
- **R10 — one date to confirm.** The TY2025 filing window ends 26-oct-2026 by business-day count,
  against 24-oct in the two prior seasons. Confirm against the definitive DIAN 2026 calendar before
  hard-coding.

## Rules whose reading the owner already closed in session

Verified personally during the Excel sessions, rule sheet still to be written: E.T. art. 55 (RAIS 25% /
2.500 UVT), art. 126-1 (the 30% cap covers voluntary pension + AFC only, and its parágrafo 3 is
pre-2013 transitional), art. 387-1 (meal allowance, both monthly conditions).
