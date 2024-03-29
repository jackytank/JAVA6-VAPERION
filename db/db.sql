USE [java6_assm_db]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[id] [int] IDENTITY(100001,1) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_categories] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[id] [bigint] IDENTITY(100001,1) NOT NULL,
	[order_id] [bigint] NOT NULL,
	[product_id] [int] NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_order_details] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [bigint] IDENTITY(100001,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[create_date] [datetime] NOT NULL,
	[address] [nvarchar](100) NOT NULL,
	[payment_method] [nvarchar](50) NOT NULL,
	[order_status] [nvarchar](50) NOT NULL,
	[total] [float] NOT NULL,
 CONSTRAINT [PK_orders] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[id] [int] IDENTITY(100001,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[image] [nvarchar](50) NOT NULL,
	[price] [float] NOT NULL,
	[create_date] [date] NOT NULL,
	[available] [bit] NOT NULL,
	[category_id] [int] NOT NULL,
 CONSTRAINT [PK_products] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_roles] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[statistics]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[statistics](
	[id] [nvarchar](50) NOT NULL,
	[value] [bigint] NOT NULL,
 CONSTRAINT [PK_statistics] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(100001,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[image_url] [nvarchar](255) NULL,
	[provider] [nvarchar](50) NULL,
	[provider_id] [nvarchar](50) NULL,
	[enabled] [bit] NULL,
	[verify_code] [nvarchar](255) NULL,
	[reset_pwd_token] [nvarchar](255) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users_role]    Script Date: 8/28/2022 9:38:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users_role](
	[id] [int] IDENTITY(100001,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[role_id] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_users_role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([id], [name]) VALUES (100001, N'Tinh dầu')
INSERT [dbo].[categories] ([id], [name]) VALUES (100002, N'Pod mod')
INSERT [dbo].[categories] ([id], [name]) VALUES (100003, N'Đầu đốt')
INSERT [dbo].[categories] ([id], [name]) VALUES (100004, N'OCC')
INSERT [dbo].[categories] ([id], [name]) VALUES (100005, N'Phụ kiện')
SET IDENTITY_INSERT [dbo].[categories] OFF
SET IDENTITY_INSERT [dbo].[order_details] ON 

INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110015, 110006, 100001, 340000, 5)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110016, 110006, 100003, 350000, 2)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110017, 110006, 100004, 350000, 3)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110018, 110006, 100007, 150000, 2)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110019, 110006, 100008, 300000, 1)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110020, 110006, 100009, 900000, 2)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110026, 110008, 100003, 350000, 1)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110027, 110008, 100004, 350000, 1)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110028, 110008, 100005, 350000, 1)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110029, 110009, 100001, 340000, 2)
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity]) VALUES (110030, 110009, 100003, 350000, 1)
SET IDENTITY_INSERT [dbo].[order_details] OFF
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([id], [user_id], [create_date], [address], [payment_method], [order_status], [total]) VALUES (110006, 100003, CAST(N'2022-08-20T00:00:00.000' AS DateTime), N'Sao hoa, 97', N'cod', N'success', 0)
INSERT [dbo].[orders] ([id], [user_id], [create_date], [address], [payment_method], [order_status], [total]) VALUES (110008, 100001, CAST(N'2022-08-28T00:00:00.000' AS DateTime), N'', N'cod', N'processing', 1050000)
INSERT [dbo].[orders] ([id], [user_id], [create_date], [address], [payment_method], [order_status], [total]) VALUES (110009, 100001, CAST(N'2022-08-28T00:00:00.000' AS DateTime), N'aso hoa', N'cod', N'processing', 1030000)
SET IDENTITY_INSERT [dbo].[orders] OFF
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100001, N'San Cristobil Coffee 30ml', N'san-cristobil-coffee.webp', 340000, CAST(N'2022-08-09' AS Date), 1, 100001)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100003, N'Sweet 21 Straw Melon 100ml', N'sweet21-straw-melon.webp', 350000, CAST(N'2022-08-09' AS Date), 1, 100001)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100004, N'Sweet 21 Peach 100ml', N'sweet21-peach.webp', 350000, CAST(N'2022-08-09' AS Date), 1, 100001)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100005, N'Dotmod DotAIO V2', N'dotmod-dotaio-v2.webp', 350000, CAST(N'2022-08-09' AS Date), 1, 100004)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100006, N'OCC Thelema Solo 100W', N'occ-thelema-solo.webp', 80000, CAST(N'2022-08-09' AS Date), 1, 100004)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100007, N'Nón lưỡi trai', N'non-luoi-trai.webp', 150000, CAST(N'2022-08-09' AS Date), 1, 100005)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100008, N'Áo thun', N'ao-thun.webp', 300000, CAST(N'2022-08-09' AS Date), 1, 100005)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100009, N'DP Mods Dovpo V3 RDA', N'dpmods-dovpo-v3.webp', 900000, CAST(N'2022-08-09' AS Date), 1, 100003)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100010, N'Blitz Ghoul RDA', N'blitz-ghoul-rda.webp', 250000, CAST(N'2022-08-09' AS Date), 1, 100003)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100011, N'Aspire Flexus Pot Kit', N'aspire-flexus-podkit.webp', 600000, CAST(N'2022-08-09' AS Date), 1, 100002)
INSERT [dbo].[products] ([id], [name], [image], [price], [create_date], [available], [category_id]) VALUES (100012, N'OXVA Origin 2 Pod Kit', N'oxva-origin2-podkit.webp', 1000000, CAST(N'2022-08-09' AS Date), 1, 100002)
SET IDENTITY_INSERT [dbo].[products] OFF
INSERT [dbo].[roles] ([id], [name]) VALUES (N'CUST', N'Customers')
INSERT [dbo].[roles] ([id], [name]) VALUES (N'DIRE', N'Directors')
INSERT [dbo].[roles] ([id], [name]) VALUES (N'STAF', N'Staffs')
INSERT [dbo].[statistics] ([id], [value]) VALUES (N'online_users', 0)
INSERT [dbo].[statistics] ([id], [value]) VALUES (N'total_page_views', 0)
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [username], [password], [email], [phone], [image_url], [provider], [provider_id], [enabled], [verify_code], [reset_pwd_token]) VALUES (100000, N'batman1', N'$2a$10$tDsKau0qy2Y8/9uEU0Dqo.w91lCdzrKwg41cSsQ1i0aGc82oNtrFm', N'batman1@gmail.com', N'0384758834', N'batman.jpg', N'DATABASE', NULL, 1, NULL, NULL)
INSERT [dbo].[users] ([id], [username], [password], [email], [phone], [image_url], [provider], [provider_id], [enabled], [verify_code], [reset_pwd_token]) VALUES (100001, N'batman2', N'$2a$10$tDsKau0qy2Y8/9uEU0Dqo.w91lCdzrKwg41cSsQ1i0aGc82oNtrFm', N'batman2@gmail.com', N'0857787745', N'batman.jpg', N'DATABASE', NULL, 1, NULL, NULL)
INSERT [dbo].[users] ([id], [username], [password], [email], [phone], [image_url], [provider], [provider_id], [enabled], [verify_code], [reset_pwd_token]) VALUES (100003, N'To Minh Tri (FPL HCM_K16)', NULL, N'tritmps15506@fpt.edu.vn', NULL, N'https://lh3.googleusercontent.com/a/AItbvml5tjh7bO0rEgACy61G8hZGz3SQWQGceIhFOBA=s96-c', N'GOOGLE', NULL, 1, NULL, N'')
INSERT [dbo].[users] ([id], [username], [password], [email], [phone], [image_url], [provider], [provider_id], [enabled], [verify_code], [reset_pwd_token]) VALUES (101040, N'batman3', N'$2a$10$WLdpMkDhvFjFmSl6e6tC1uVjcze5n0oP5sPeEMnPpfOuS853LPNni', N'jackytank@gmail.com', N'0374657743', N'upload-1660983901628-burd.jpg', N'DATABASE', NULL, 1, NULL, NULL)
SET IDENTITY_INSERT [dbo].[users] OFF
SET IDENTITY_INSERT [dbo].[users_role] ON 

INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (100001, 100000, N'CUST')
INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (100002, 100001, N'STAF')
INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (100008, 100001, N'DIRE')
INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (100010, 100001, N'CUST')
INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (101043, 101040, N'STAF')
INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (101044, 101040, N'DIRE')
INSERT [dbo].[users_role] ([id], [user_id], [role_id]) VALUES (101045, 101040, N'STAF')
SET IDENTITY_INSERT [dbo].[users_role] OFF
ALTER TABLE [dbo].[order_details] ADD  DEFAULT ((0)) FOR [price]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT ((1)) FOR [quantity]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT ('') FOR [payment_method]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT ('') FOR [order_status]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT ((0)) FOR [total]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT (N'default-product.jpg') FOR [image]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT ((0)) FOR [price]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT ((1)) FOR [available]
GO
ALTER TABLE [dbo].[statistics] ADD  DEFAULT ((0)) FOR [value]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [enabled]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_5] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_5]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_6] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_6]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_4] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_4]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_3] FOREIGN KEY([category_id])
REFERENCES [dbo].[categories] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_3]
GO
ALTER TABLE [dbo].[users_role]  WITH CHECK ADD  CONSTRAINT [FK_1] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[users_role] CHECK CONSTRAINT [FK_1]
GO
ALTER TABLE [dbo].[users_role]  WITH CHECK ADD  CONSTRAINT [FK_2] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[users_role] CHECK CONSTRAINT [FK_2]
GO
