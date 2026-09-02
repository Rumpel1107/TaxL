# R3 — INCRNGO: mandatory contributions

**Source:** art. 55 E.T. (Ley 1943 de 2018 art. 23) · art. 56 E.T. · Ley 100 de 1993 arts. 20
and 25–27 · DIAN Oficio 909171 de 2021

## Values

| Tax year | 2.500 UVT — voluntary RAIS |
|---|---|
| 2023 | $106.030.000 |
| 2024 | $117.662.500 |
| 2025 | $124.497.500 |

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
- The Fondo de Solidaridad Pensional is an additional point of the mandatory pension contribution
  itself — Ley 100 de 1993 arts. 20 and 25–27 — so it is INCRNGO under art. 55 with no cap, and
  is declared aggregated with pension in line 33 of Form 210. No DIAN Concepto rules on it
  directly: Oficio 909171 de 2021 certifies both under art. 55 incidentally, and line 54 of
  Form 220 reports them as a single figure (R18).
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
`incrngo_rais` = $124.497.500
