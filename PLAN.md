# TaxL — Income Tax Return Simulator (Individual Taxpayers · Colombia)

> **What goes in this document:** what the product is, who it is for, and what it deliberately
> is not. Nothing else.
>
> Not here: the roadmap (`docs/roadmap.md`), settled decisions (`docs/decisions.md`), history
> (`docs/log.md`), the tax rules (`docs/domain/`). Process — phases and gates — is the `/method`
> skill.
>
> Owner: Rumpel · Last updated: 2026-09-10

---

## 1. Objective

**General objective.** Build a simulator that allows an individual taxpayer in Colombia to autonomously obtain a **reliable draft** of their income tax return, with field-by-field explanations and recommendations, positioned as an **educational / pre-check tool** (not as official tax-filing software) — monetized through a free-to-paid funnel (§3).

**MVP objective (Phase 0).** Validate a **deterministic calculation engine in Excel**, for the **employee** profile, tax year **2025**, confirming it reproduces, to the peso, already-filed tax returns.

**Why this scope.** The highest-risk piece (the tax logic) is tackled first, with the cheapest medium (Excel) and free ground truth (own past returns). UI, document ingestion, and AI come later — on top of an engine already known to be correct.

---

## 2. Product North Star and Value Proposition

> **North Star: "Understand and verify before filing."**

The DIAN already offers the **suggested return**: a **pre-filled** form based on what third parties reported, which the user reviews, edits, or accepts — but **any inconsistency remains the taxpayer's responsibility**, and it's only available to some profiles.

The simulator **does not compete on "filling out the form"** (the DIAN already does that). It competes in the layer **before that**: making the person **understand and trust** the numbers before accepting anything official.

**Differentiators vs. the suggested return**

| # | The suggested return... | The simulator... |
|---|--------------------------|-------------------|
| 1 | Pre-fills, doesn't teach | **Explains** every field (tooltips) |
| 2 | Reflects only what third parties reported | **Reconciles** third-party data (exógena) vs. your actual documents and flags third-party errors |
| 3 | Doesn't pick the best option for you | **Optimizes**: compares scenarios (real costs / 25% exempt income / presumptive costs) |
| 4 | Doesn't warn about risk | **Flags risk**: wealth/equity comparison, the "moving money between your own accounts" trap |
| 5 | Is the official act (with legal liability) | Is a **safe pre-check**, a draft before touching anything official |

> In one sentence: the suggested return gives you a form to **accept**; the simulator gives you the understanding to **trust** it.

**North Star Metric (candidate, once there are real users).** % of users who complete a draft and report feeling able to **verify** their own return. In the MVP (Excel only, own data) the north star translates into an operational proxy: **every number in the Output is explainable and traceable** to an input or a parameter.

---

## 3. Monetization Funnel

**Design insight (session 2026-07-06):** nobody enters their data with discipline. In the product
(P2+), data enters **by file, not by form**: the user downloads their exógena report from the DIAN
portal (guided by a curated YouTube tutorial — curate an existing one, don't produce) and uploads it.
Manual entry remains available as fallback and is the P0-Excel mechanism.

| Tier | Deliverable | Price |
|---|---|---|
| **Free (touchpoint)** | Conservative tax estimate WITHOUT deductions (only what's inferable from the file) + filing deadline by ID digits + applicable rules/thresholds. Framed explicitly as **"conservative estimate / maximum scenario"** — legitimate anchoring, never alarmism. | $0 |
| **X (core)** | Full liquidation + deduction validation with legal caps + step-by-step normative breakdown + supporting-documents checklist. | One-time per season (hypothesis: 10–20% of an accountant's fee; validate with M5) |
| **Y (upsell, post-MVP)** | Recommendations to reduce next year's tax (AFC, voluntary pension, etc.). ⚠️ Closest to "tax advisory" — gated on the legal-boundary research task. | Additional payment |

Funnel mechanics: the free no-deductions estimate is deliberately the **ceiling**; X literally sells
"see how much you can lower it". **The deduction IS the product.** Every line the engine computes
carries its Form 210 box number from the first slice on (D42, superseding D07); what stays in a
later phase is producing the form itself.

---

## 4. Success Metrics and "Validated" Criteria

| # | Metric | MVP Target |
|---|--------|------------|
| M1 | Match with real tax returns | **$0** difference on key lines of Form 210, across the **3** reference returns held in the local fixture |
| M2 | Test case coverage | 100% of the worked examples from the reference webinar (employee and self-employed) reproduced correctly |
| M3 | Parameterization | The engine switches tax year by editing **only** the Parameters sheet |
| M4 | Traceability | Every Output value can be traced back to an Input cell or a parameter |
| M5 | Willingness-to-pay signal *(new)* | Show the validated Excel result to ≥3 people in the target profile; record: would pay yes/no + how much |

> **MVP closing rule:** the MVP is not "done" until M1 and M2 are met. M5 gates the decision to
> invest in P2 (product), not the closing of P0.

---

## 5. Scope

**In scope for MVP (Phase 0)**
- **Employee** profile (with or without freelance / professional fees income opting for the 25% exempt income benefit).
- General schedule → labor income sub-schedule.
- Filing-obligation determination via the 5 thresholds: income, net worth, credit-card spending, bank movements, purchases.
- Tax liability calculation (Art. 241 table).
- Net worth comparison.
- Output mapped to Form 210 lines.
- **Manual** data entry *(P0 only — the product entry point is the exógena file, see §3 and P2)*.

**Out of scope for MVP (later phases)**
- Self-employed with real costs/expenses and presumptive costs (Resolution 532); capital income; non-labor income; dividends; complex occasional gains (inheritances).
- OCR of individual certificates *(exógena parsing is NOT out of scope anymore — it moved to P2)*.
- Login, persistence of the user's figures, AI-driven recommendations (tier Y).
- Wealth tax and advance tax payment — modeled but not prioritized in v0.
- Third-party personal data handling (Law 1581) beyond the consented email: the first slice already stores one, so the minimum notice applies from it (D57); what accounts and payment add is revisited when they exist.

---

## 6. Phases (Epics)

| Phase | Epic | Expected Outcome | Main Risk it Reduces |
|-------|------|-------------------|------------------------|
| **P0** | Calculation engine in Excel (employee, TY2025) | Excel that matches real filed returns + WTP signal (M5) | Incorrect tax calculation; building without demand |
| **P1** | Professional validation + coverage expansion | Engine reviewed by an accountant; new profiles added | Regulatory errors; scope |
| **P2** | Software prototype **+ exógena ingestion** *(moved from P3)* | App with the funnel's free tier live: exógena upload → parse → depurate → conservative estimate + deadline + rules. Paid tier X on top. Login, disclaimer, persistence. Manual entry as fallback | Personal data (Law 1581); **exógena depuration (R11–R14) — now the #1 technical unknown** |
| **P3** | Certificate ingestion (OCR) + explanation layer | OCR of individual certificates; tooltips, alerts | Heterogeneous OCR |
| **P4** | Recommendation layer (AI) — tier Y | Optimization scenarios, next-year recommendations | Trusting the AI with the calculation; legal boundary |

**Cross-cutting principles (apply to all phases):**
1. **The engine is deterministic.** AI never calculates the tax; it only extracts documents, explains, and recommends.
2. **Everything is parameterized by tax year.** Parameter *values* are data, never hardcoded — and every value requires its rule sheet (`docs/domain/rules.md`) before being trusted, regardless of source.
3. **The "exógena" report is the gold-standard input** (Excel file, includes the 5-threshold summary) — and the **product's entry point**, not a later enhancement.
4. **Educational / pre-check positioning**, always with a disclaimer and a recommendation to seek professional validation.

---

## 7. Risk Register

> Scale: Prob./Impact = Low / Medium / High. **Trigger** = the signal that warns the risk is materializing.

| ID | Risk | Prob. | Impact | Mitigation | Trigger |
|----|------|-------|--------|------------|---------|
| R-1 | **Scope creep** | High | High | Cut by profile (employee first); prioritized backlog; explicit out-of-scope; MVP closing rule (M1+M2) | Tasks appear not tied to M1/M2 |
| R-2 | **Incorrect tax calculation** | Medium | High | Validation suite vs real returns; rule sheets (US-P0-000); professional review (P1) | A test case shows non-$0 difference |
| R-3 | **Regulatory changes** | High | Medium | Everything parameterized by year; isolated Parameters sheet | New reform / decree |
| R-4 | **Delegating the calculation to AI** | Medium | High | Hard rule: deterministic engine; AI only extracts/explains | Someone proposes "let the LLM calculate it" |
| R-5 | **Exógena depuration fails (false income)** | High | High | R11–R14 rule sheets calibrated against real exógena↔declaration pairs; conservative framing; show ranges when ambiguous; manual correction always available | Free estimate deviates grossly from a known real return |
| R-6 | **Third-party personal data (Law 1581)** | Medium | High | Consent, privacy notice and deletion channel from the first slice (D57); educational positioning | Met: the first user other than the owner who consents to give an email |
| R-7 | **TY2025 exógena not yet available (~July)** | High | Low | Work P0 with 2024 data / own past returns | Calendar |
| R-8 | **Over-engineering the methodology** | Medium | Medium | Lightweight Kanban; weekly review; no heavy ceremonies | The board stops being updated |
| R-9 | **Legal boundary: "estimation tool" vs "tax advisory"** *(new)* | Medium | High | Research before charging (blocks tier Y; informs X disclaimer) | Drafting the X paywall copy |
| R-10 | **Extreme seasonality** *(new)* | High | Medium | Aug–Oct peak dates the go-to-market backwards from R10 (calendar rule); off-season demand ≈ 0 | Product ready after the season's peak |

---

## 8. Quick Glossary

- **UVT** (Unidad de Valor Tributario): Colombia's Tax Value Unit (2025: 49,799 COP — pending rule sheet). Converts thresholds into pesos.
- **INCRNGO**: Non-taxable income (Ingreso No Constitutivo de Renta ni Ganancia Ocasional).
- **General schedule** (cédula general): groups labor, capital, and non-labor income.
- **Exógena**: third-party information reported to the DIAN; includes the 5-threshold summary. It is the product's entry point.
- **Suggested return** (declaración sugerida): DIAN's pre-filled form based on exógena; taxpayer remains responsible.
- **Form 210**: income tax return for resident individual taxpayers.
- **Net worth comparison** (comparación patrimonial): DIAN's control over year-over-year net worth growth.
