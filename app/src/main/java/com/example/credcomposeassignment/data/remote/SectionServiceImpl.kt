package com.example.credcomposeassignment.data.remote

import com.example.credcomposeassignment.data.models.CategoryItem
import com.example.credcomposeassignment.data.models.CategoryProperty
import com.example.credcomposeassignment.data.models.Section
import com.example.credcomposeassignment.data.models.SectionProperty
import com.example.credcomposeassignment.data.models.SectionResponse
import kotlinx.coroutines.delay

class SectionServiceImpl : SectionService {

    override suspend fun getSections(): SectionResponse {
        return buildResponse()
    }

    private fun buildResponse(): SectionResponse {
        return SectionResponse(
            listOf(
                Section(
                    id = "money_section",
                    sectionProperties = SectionProperty(
                        title = "MONEY",
                        categories = listOf(
                            CategoryItem(
                                id = "mint",
                                CategoryProperty(
                                    name = "Mint",
                                    description = "Grow your savings. 3x faster.",
                                    iconUrl = "https://imgur.com/a/oAqg5VO"
                                )
                            ),
                            CategoryItem(
                                id = "bank_accounts",
                                CategoryProperty(
                                    name = "Bank accounts",
                                    description = "Check your bank balance",
                                    iconUrl = "https://imgur.com/a/HesCgkz"
                                )
                            ),
                            CategoryItem(
                                id = "scan_n_pay",
                                CategoryProperty(
                                    name = "Scan N Pay",
                                    description = "Scan and Pay",
                                    iconUrl = "https://imgur.com/a/9sRxq9d"
                                )
                            ),
                        )
                    )
                ),
                Section(
                    id = "bills_section",
                    sectionProperties = SectionProperty(
                        title = "BILLS",
                        categories = listOf(
                            CategoryItem(
                                id = "utility_n_bills",
                                CategoryProperty(
                                    name = "Utility & all bills",
                                    description = "The most rewarding way to pay your bills.",
                                    iconUrl = "https://imgur.com/qHTSB1A"
                                )
                            ),
                            CategoryItem(
                                id = "house_rent",
                                CategoryProperty(
                                    name = "House rent",
                                    description = "Pay rent with your credit card",
                                    iconUrl = "https://imgur.com/a/TBGf2Lo"
                                )
                            ),
                            CategoryItem(
                                id = "education_fees",
                                CategoryProperty(
                                    name = "Education fees",
                                    description = "Pay education fees with your credit card",
                                    iconUrl = "https://imgur.com/a/mYM5Q6T"
                                )
                            ),
                        )
                    )
                ),
                Section(
                    id = "benefits_section",
                    sectionProperties = SectionProperty(
                        title = "BENEFITS",
                        categories = listOf(
                            CategoryItem(
                                id = "rewards",
                                CategoryProperty(
                                    name = "Rewards",
                                    description = "Redeem coins for cashback",
                                    iconUrl = "https://imgur.com/a/NGCTPMV"
                                )
                            ),
                            CategoryItem(
                                id = "refer_n_earn",
                                CategoryProperty(
                                    name = "Refer & Earn",
                                    description = "Assured cashback for bringing friends to CRED",
                                    iconUrl = "https://imgur.com/a/7O9OOdY"
                                )
                            ),
                            CategoryItem(
                                id = "coins",
                                CategoryProperty(
                                    name = "Coins",
                                    description = "Use coins to claim rewards and other perks",
                                    iconUrl = "https://imgur.com/a/RScQ61d"
                                )
                            ),
                            CategoryItem(
                                id = "vouchers",
                                CategoryProperty(
                                    name = "Vouchers",
                                    description = "Vouchers you have won",
                                    iconUrl = "https://imgur.com/a/AluJMed"
                                )
                            ),
                            CategoryItem(
                                id = "brand_offers",
                                CategoryProperty(
                                    name = "Brand offers",
                                    description = "Get exclusive offers on brands while using CRED pay",
                                    iconUrl = "https://imgur.com/a/RScQ61d"
                                )
                            ),
                        )
                    )
                ),
            )
        )
    }

}