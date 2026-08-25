# Constitution

What this project never negotiates. Principles only — how we work together is `AGENTS.md`,
what phase follows which is the `/method` skill, and what is pending is `docs/roadmap.md`.

This project builds a tax return simulator for Colombian individuals (Formulario 210).

1. DETERMINISTIC ENGINE: All tax calculations are performed by a deterministic, testable engine. AI/LLMs never calculate taxes — they only extract data, explain results, and assist the user.
2. DOMAIN RULES ARE OWNER-VALIDATED [R]: Tax rules (docs/domain/rules.md) are personally understood, documented and validated by the project owner against primary sources (Estatuto Tributario, DIAN resolutions, decrees). Agents may research and propose, but never decide domain rules. Every rule ships with: rule in own words + numeric example + test case.
3. GOLDEN TESTS ARE INVIOLABLE: The engine must reproduce the owner's three real filed tax returns (AG2023, AG2024, AG2025) to the peso. Expected values live in the local, non-versioned fixture — never in this repo. Any change that breaks a golden test is rejected. Non-regression suite runs on every change.
4. EVERYTHING PARAMETERIZED BY TAX YEAR: Legal values (UVT, caps, rates, brackets) are versioned configuration data, never hardcoded. Parameters must be editable by non-technical users (accountants) through a dedicated interface.
5. SOURCE DOCUMENTS OVER EXOGENA: The exogena file is the entry point, but official certificates (Form 220, bank certificates) take priority when they conflict. Discrepancies are always shown to the user, who makes the final call. The system never silently picks a side.
6. FAIL LOUDLY: When input is outside the supported scope (non-salaried income, unsupported cedulas), the system alerts and refuses to produce a misleading result. Never fail silently. When data is ambiguous, show ranges, not false precision.
7. FULL TRACEABILITY: Every output number must be traceable to an input document or a legal parameter. Every calculation links to the legal article that supports it (E.T. articles with links). Include information even when it does not change the result — completeness builds trust.
8. EDUCATIONAL POSITIONING: The product is a simulator/pre-check tool, not tax advisory nor official filing software. Always disclose this and recommend professional validation. It prepares users for their accountant; it does not replace them.
9. INCREMENTAL SCOPE: MVP automates the owner's exact case (salaried employee, cedula general) with out-of-scope detection. Coverage expands case by case as real scenarios appear, never speculatively.
10. GROUND TRUTH IS RECONSTRUCTED: Every figure is reconstructed from the original source documents, never copied from a prior result or from the previous year's return. This is what surfaces errors in returns already filed; copying forward hides them.
