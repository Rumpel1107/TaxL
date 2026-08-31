# R3 — INCRNGO: mandatory contributions

**Source:** art. 55 E.T. (Ley 1943 de 2018 art. 23) · art. 56 E.T.

## Values

| Tax year | 2.500 UVT — voluntary RAIS |
|---|---|
| 2023 | $106.030.000 |
| 2024 | $117.663.000 |
| 2025 | $124.498.000 |

## Formula

    incrngo_mandatory = mandatory_health + mandatory_pension + solidarity_fund
    incrngo_rais      = min(contributed, 0.25 × labor_income, 2500 × uvt(tax_year))
    incrngo           = incrngo_mandatory + incrngo_rais

## Conditions

- Mandatory health and pension carry no individual cap.
- INCRNGO is subtracted before the base of the general cap is computed (R7) and before the
  base of the 25% exemption (R4), so it is never subject to the 40% cap.
- Voluntary RAIS contributions are INCRNGO, not exempt income, and carry their own cap: 25% of
  labor income and 2.500 UVT, the lower governing.
- The Fondo de Solidaridad Pensional is treated as part of the mandatory pension contribution.
  R18 carries the flag that this rests on doctrine, with no numbered DIAN Concepto.
- The 2.500 UVT cap lands on an exact half-thousand in 2024 and 2025; which way it rounds
  depends on R1's open question.
- **Open:** state educational support (art. 46 E.T.) is also treated as INCRNGO by the engine
  and is not in the registry.

## Test case

**Given** mandatory health of $4.000.000, mandatory pension of $6.000.000 and solidarity fund
contributions of $500.000, tax year 2025
**When** INCRNGO resolves → $10.500.000 in full; no cap applies

**Given** labor income of $100.000.000 and voluntary RAIS contributions of $30.000.000, tax
year 2025
**When** the cap resolves → the 25% governs and `incrngo_rais` = $25.000.000

**Given** labor income of $600.000.000 and voluntary RAIS contributions of $200.000.000, tax
year 2025
**When** the cap resolves → 25% would be $150.000.000, so the 2.500 UVT ceiling governs and
`incrngo_rais` = $124.498.000
