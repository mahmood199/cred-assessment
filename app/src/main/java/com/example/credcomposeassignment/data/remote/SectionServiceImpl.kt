package com.example.credcomposeassignment.data.remote

import com.example.credcomposeassignment.data.models.CategoryItem
import com.example.credcomposeassignment.data.models.CategoryProperty
import com.example.credcomposeassignment.data.models.Section
import com.example.credcomposeassignment.data.models.SectionProperty
import com.example.credcomposeassignment.data.models.SectionResponse

class SectionServiceImpl : SectionService {

    override suspend fun getSections(): SectionResponse {
        return buildResponse()
    }

    private fun buildResponse(): SectionResponse {
        return SectionResponse(
            listOf(
                Section(
                    id = "money_section",
                    sectionProperty = SectionProperty(
                        title = "MONEY",
                        categories = listOf(
                            CategoryItem(
                                id = "mint",
                                CategoryProperty(
                                    name = "Mint",
                                    description = "Grow your savings. 3x faster.",
                                    iconUrl = "https://images.unsplash.com/photo-1588908933351-eeb8cd4c4521?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "bank_accounts",
                                CategoryProperty(
                                    name = "Bank accounts",
                                    description = "Check your bank balance",
                                    iconUrl = "https://images.unsplash.com/photo-1560472355-536de3962603?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "scan_n_pay",
                                CategoryProperty(
                                    name = "Scan N Pay",
                                    description = "Scan and Pay",
                                    iconUrl = "https://images.unsplash.com/photo-1600147131759-880e94a6185f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1936&q=80"
                                )
                            ),
                        )
                    )
                ),
                Section(
                    id = "bills_section",
                    sectionProperty = SectionProperty(
                        title = "BILLS",
                        categories = listOf(
                            CategoryItem(
                                id = "utility_n_bills",
                                CategoryProperty(
                                    name = "Utility & all bills",
                                    description = "The most rewarding way to pay your bills.",
                                    iconUrl = "https://images.unsplash.com/photo-1520695625556-c2a7bfe87a2f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "house_rent",
                                CategoryProperty(
                                    name = "House rent",
                                    description = "Pay rent with your credit card",
                                    iconUrl = "https://images.unsplash.com/photo-1560518883-ce09059eeffa?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1973&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "education_fees",
                                CategoryProperty(
                                    name = "Education fees",
                                    description = "Pay education fees with your credit card",
                                    iconUrl = "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2022&q=80"
                                )
                            ),
                        )
                    )
                ),
                Section(
                    id = "benefits_section",
                    sectionProperty = SectionProperty(
                        title = "BENEFITS",
                        categories = listOf(
                            CategoryItem(
                                id = "rewards",
                                CategoryProperty(
                                    name = "Rewards",
                                    description = "Redeem coins for cashback",
                                    iconUrl = "https://images.unsplash.com/photo-1534705867302-2a41394d2a3b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1780&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "refer_n_earn",
                                CategoryProperty(
                                    name = "Refer & Earn",
                                    description = "Assured cashback for bringing friends to CRED",
                                    iconUrl = "https://images.unsplash.com/photo-1536762733888-d86cc96c10b6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1935&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "coins",
                                CategoryProperty(
                                    name = "Coins",
                                    description = "Use coins to claim rewards and other perks",
                                    iconUrl = "https://images.unsplash.com/photo-1587403335644-fa8fef06b261?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2072&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "vouchers",
                                CategoryProperty(
                                    name = "Vouchers",
                                    description = "Vouchers you have won",
                                    iconUrl = "https://images.unsplash.com/photo-1549465220-1a8b9238cd48?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2040&q=80"
                                )
                            ),
                            CategoryItem(
                                id = "brand_offers",
                                CategoryProperty(
                                    name = "Brand offers",
                                    description = "Get exclusive offers on brands while using CRED pay",
                                    iconUrl = "https://images.unsplash.com/photo-1611403570720-162d8829689a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80"
                                )
                            ),
                        )
                    )
                ),
            )
        )
    }

}